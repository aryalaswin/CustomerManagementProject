/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.response;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class CustomersResponse {
    public ResponseEntity<CustomersResponse> response(String message,HttpStatus status){
      Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestramp", LocalDate.now());
        body.put("message", message);
        body.put("Status", status);
      return new ResponseEntity(body,status);
  }
}
