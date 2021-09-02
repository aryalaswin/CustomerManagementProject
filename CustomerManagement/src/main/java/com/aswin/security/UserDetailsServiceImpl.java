/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.security;

import com.aswin.dao.LoginRepository;
import com.aswin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aswin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
int count=0;
    @Autowired
    private LoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = repository.findByUsername(username);
      
        
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return userDetails;
        }
    }

}
