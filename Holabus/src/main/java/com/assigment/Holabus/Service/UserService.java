package com.assigment.Holabus.Service;

import com.assigment.Holabus.Model.Department;
import com.assigment.Holabus.Model.User;
import com.assigment.Holabus.Repository.DepartmentRepository;
import com.assigment.Holabus.Repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository _userRepository;
    private DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository, PasswordEncoder passwordEncoder) {
        this._userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User changePassword(String email, String oldPassword, String newPassword) throws Exception {
        Optional<User> user = _userRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new Exception("User not found");
        }

        if (!passwordEncoder.matches(oldPassword, user.get().getPassword())) {
            throw new Exception("Incorrect old password");
        }

        user.get().setPassword(passwordEncoder.encode(newPassword));
        _userRepository.save(user.get());

        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return _userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));
    }

    public User getUserByEmail(String email) {
        Optional<User> userOptional = _userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
        Department department = departmentRepository.findById(user.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        user.setDepartment(department);
        return user;
    }

    public User getUserById(int id) {
        Optional<User> user = _userRepository.findById(id);
        return user.orElse(null);
    }
}
