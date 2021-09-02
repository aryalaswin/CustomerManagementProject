/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.dto;

import lombok.Data;

/**
 *
 * @author Aswin
 */
@Data
public class UserDto {

    private Long id;
  
    private String password;
   
    private String Status;
    private String username;
    private int attemptCount;
   
}
