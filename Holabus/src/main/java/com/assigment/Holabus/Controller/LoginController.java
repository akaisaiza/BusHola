package com.assigment.Holabus.Controller;

import com.assigment.Holabus.DTO.JwtResponse;
import com.assigment.Holabus.DTO.LoginDTO;
import com.assigment.Holabus.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @RequestMapping(value = "/oauth2", method = RequestMethod.POST)
    public ResponseEntity<?> loginOAuth2(JwtAuthenticationToken token) {
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("sub", token.getToken().getSubject());
        userDetails.put("name", token.getToken().getClaim("name"));
        userDetails.put("email", token.getToken().getClaim("email"));
        userDetails.put("picture", token.getToken().getClaim("picture"));
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
}
