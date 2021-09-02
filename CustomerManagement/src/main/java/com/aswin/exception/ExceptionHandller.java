/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.exception;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Aswin
 */
@ControllerAdvice
@ResponseBody

public class ExceptionHandller extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserIsNotActiveException.class)

    ResponseEntity<?> errorhandeller(UserIsNotActiveException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestramp", LocalDate.now());
        body.put("status", ex.status);
        body.put("error", ex.message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("error", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CustomerNotActiveException.class)

    public ResponseEntity<?> excepton(CustomerNotActiveException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestramp", LocalDate.now());
        body.put("error", ex.message);
        body.put("Status", ex.status);
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> excepton(BadCredentialsException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("date", LocalDate.now());
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)

    public ResponseEntity<?> excepton(UsernameNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("date", LocalDate.now());
        body.put("reason", "Not Authorized");
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EditUnsuccessException.class)
    public ResponseEntity<?> excepton(EditUnsuccessException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("date", LocalDate.now());
        body.put("error", ex.message);
        body.put("Status", ex.status);
        return new ResponseEntity<>(body, ex.status);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<?> excepton(DataIntegrityViolationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", ex.getMessage());
        body.put("date",LocalDate.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> excepton(ResourceNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestramp", LocalDate.now());
        body.put("error", ex.message);

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

}
