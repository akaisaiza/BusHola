package com.assigment.Holabus.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.assigment.Holabus.DTO.OvertimeBusDTO;
import com.assigment.Holabus.Model.Bus;
import com.assigment.Holabus.Model.User;
import com.assigment.Holabus.Service.BusService;
import com.assigment.Holabus.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.assigment.Holabus.Model.Booking;
import com.assigment.Holabus.Service.BookingService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService _bookingService;
    private final UserService _userService;
    private final BusService _busService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService, BusService busService) {
        _bookingService = bookingService;
        _userService = userService;
        _busService = busService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return _bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable int id) {
        return _bookingService.getBookingById(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return _bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable int id, @RequestBody Booking booking) {
        return _bookingService.updateBooking(id, booking);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBooking(@PathVariable int id) {
        return _bookingService.deleteBooking(id);
    }

    @RequestMapping(value = "/overtime", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> bookingOvertimeBus(@Valid OvertimeBusDTO overtimeBusDTO) {
        Booking booking = new Booking();
        booking.setCreateDate(LocalDate.now());
        booking.setStartDate(LocalDate.parse(overtimeBusDTO.getDate()));
        booking.setEndDate(LocalDate.parse(overtimeBusDTO.getDate()).plusMonths(1));
        booking.setPurpose(overtimeBusDTO.getPurpose());
        booking.setRequester((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        booking.setPurpose(overtimeBusDTO.getPurpose());
        booking.setReqStatus("Pending");
        booking.setPhoneNumber(overtimeBusDTO.getPhoneNumber());
        if (overtimeBusDTO.isBookMultipleAccount()) {
            overtimeBusDTO.getBookAccount().forEach((obj) -> {
                User user = _userService.getUserById(obj.getId());
                if (user != null) {
                    booking.setUser(user);
                    _bookingService.createBooking(booking);
                }
            });
        }
        booking.setBus(_busService.getBusByBusNo(overtimeBusDTO.getBusRoute()));
        booking.setStatus("Pending");
        booking.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        booking.setProject("Overtime");
        _bookingService.createBooking(booking);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listRequestBooking() {
        List<Booking> bookings = _bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
}
