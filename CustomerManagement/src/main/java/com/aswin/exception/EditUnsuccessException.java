/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Aswin
 */
@AllArgsConstructor
public class EditUnsuccessException extends RuntimeException{
    String message;
    HttpStatus status;
    
}