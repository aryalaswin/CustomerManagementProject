/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.dao;

import com.aswin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aswin
 */
@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

     public User findByUsername(String username);
    
   
    
}
