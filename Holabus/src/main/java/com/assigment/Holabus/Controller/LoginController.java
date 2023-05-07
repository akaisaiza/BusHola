package com.assigment.Holabus.Controller;

import com.assigment.Holabus.DTO.JwtResponse;
import com.assigment.Holabus.DTO.LoginDTO;
import com.assigment.Holabus.Model.User;
import com.assigment.Holabus.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/public")
public class LoginController {

    private final AuthenticationManager _authenticationManager;
    private final JwtUtils _jwtUtils;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        _authenticationManager = authenticationManager;
        _jwtUtils = jwtUtils;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = _authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = _jwtUtils.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, user.getRole().getRoleName()));
    }
}
