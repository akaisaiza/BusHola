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
    @Autowired
    private DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this._userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User changePassword(String email, String oldPassword, String newPassword) throws Exception {
        User user = _userRepository.findByEmailchangepassUser(email);

        if (user == null) {
            throw new Exception("User not found");
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new Exception("Incorrect old password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        _userRepository.save(user);

        return user;
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

}
