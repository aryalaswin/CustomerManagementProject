package com.aswin.controller;

import com.aswin.dao.LoginRepository;
import com.aswin.dto.UserDto;
import com.aswin.jwt.JwtRequest;
import com.aswin.jwt.JwtUtil;
import com.aswin.response.UserResponse;
import com.aswin.security.UserDetailsServiceImpl;
import com.aswin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aswin
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private LoginRepository repository;
    @Autowired
    LoginService loginservice;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        return loginservice.generateToken(jwtRequest);
    }

    @PostMapping("/login")
    public UserResponse createUser(@RequestBody UserDto user) {
         return loginservice.saveUser(user);
        
    }

}
