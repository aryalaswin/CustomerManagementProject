/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.serviceimpl;

import com.aswin.dao.LoginRepository;
import com.aswin.dto.UserDto;
import com.aswin.entity.User;
import com.aswin.exception.UserIsNotActiveException;
import com.aswin.jwt.JwtRequest;
import com.aswin.jwt.JwtResponse;
import com.aswin.jwt.JwtUtil;
import com.aswin.response.UserResponse;
import com.aswin.security.UserDetailsServiceImpl;
import com.aswin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aswin
 */
@Service
public class LoginServiceImpl implements LoginService {

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

    @Override
    public ResponseEntity<?> generateToken(JwtRequest jwtRequest) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            int count = 0;
            User user = repository.findByUsername(jwtRequest.getUsername());
            if (user == null) {
                throw new UsernameNotFoundException("Invalid username or password");
            }
            count = user.getAttemptCount();
            count++;
            user.setAttemptCount(count);
            repository.save(user);
            throw new BadCredentialsException("Invalid password");

        }
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        User user = repository.findByUsername(jwtRequest.getUsername());
        if (user.getStatus().equalsIgnoreCase("ACTIVE")) {
            user.setAttemptCount(0);
            repository.save(user);
            String token = this.jwtUtil.generateToken(userDetails);
//        //System.out.println("JWT " + token);
            System.out.println(jwtUtil.extractAllClaims(token));
            System.out.println(new JwtResponse(token));
            return ResponseEntity.ok(new JwtResponse(token));

        } else {
            throw new UserIsNotActiveException("The entered username is not active", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserResponse saveUser(UserDto user) {
        User u = new User();
        u.setStatus("ACTIVE");
        u.setAttemptCount(0);
        u.setUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(u);
        return new UserResponse("User Added Successfully");
       
    }

}
