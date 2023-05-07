package com.assigment.Holabus.Controller;

import com.assigment.Holabus.DTO.UserDTO;
import com.assigment.Holabus.Service.DepartmentService;
import com.assigment.Holabus.Service.RoleService;
import com.assigment.Holabus.Utils.ResponseHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.assigment.Holabus.Model.User;
import com.assigment.Holabus.Service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder _passwordEncoder;
    private final RoleService _roleService;
    private final DepartmentService _departmentService;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService, DepartmentService departmentService) {
        this.userService = userService;
        _passwordEncoder = passwordEncoder;
        _roleService = roleService;
        _departmentService = departmentService;
    }

    @PatchMapping("/change-password")
    public ResponseEntity<User> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        try {
            User user = userService.changePassword(email, oldPassword, newPassword);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(_passwordEncoder.encode(userDTO.getPassword()));
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(_roleService.getUserRole());
        user.setDepartment(_departmentService.getDepartmentById(userDTO.getDepId()));
        if (user.getDepartment() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseHandling.addMessage("message", "Department not found, please try again."));
        }
        if (userService.addUser(user)) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ResponseHandling.addMessage("message", "Created successfully."));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseHandling.addMessage("message", "There is an error occurred, please try again."));
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestBody int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResponseHandling.addMessage("message", "There is no user within our database, please try again."));
        }
        if (userService.deleteUser(user)) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseHandling.addMessage("message", "There is error when deleting the user, please try again later."));
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
