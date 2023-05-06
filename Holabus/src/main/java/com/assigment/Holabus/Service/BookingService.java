package com.assigment.Holabus.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.Holabus.Model.Booking;
import com.assigment.Holabus.Repository.BookingRepository;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookingRepository.findAll().forEach(bookings::add);
        return bookings;
    }

    public Booking getBookingById(int id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.isPresent() ? bookingOptional.get() : null;
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(int id, Booking booking) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking existingBooking = bookingOptional.get();
            existingBooking.setStartDate(booking.getStartDate());
            existingBooking.setEndDate(booking.getEndDate());
            existingBooking.setCreateDate(booking.getCreateDate());
            existingBooking.setProject(booking.getProject());
            existingBooking.setStatus(booking.getStatus());
            existingBooking.setReqStatus(booking.getReqStatus());
            existingBooking.setPurpose(booking.getPurpose());
            existingBooking.setRequester(booking.getRequester());
            existingBooking.setUser(booking.getUser());
            existingBooking.setBus(booking.getBus());

            return bookingRepository.save(existingBooking);
        } else {
            return null;
        }
    }

    public boolean deleteBooking(int id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking existingBooking = bookingOptional.get();
            bookingRepository.delete(existingBooking);
            return true;
        } else {
            return false;
        }
    }
}
