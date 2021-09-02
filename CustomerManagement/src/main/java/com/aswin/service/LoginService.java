/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.service;

import com.aswin.dto.UserDto;
import com.aswin.entity.User;
import com.aswin.jwt.JwtRequest;
import com.aswin.response.UserResponse;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Aswin
 */
public interface LoginService {
public ResponseEntity<?> generateToken(JwtRequest jwtRequest); 
public UserResponse saveUser(UserDto user);
}
