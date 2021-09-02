/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.controller;

import com.aswin.dto.CustomerDto;
import com.aswin.dto.CustomerFamilyDto;
import com.aswin.response.CustomerResponse;
import com.aswin.response.CustomersResponse;
import com.aswin.service.CustomerService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aswin
 */
@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomersResponse response;

    @PostMapping("/post")
    private ResponseEntity<?> postCustomer(@Valid @RequestBody CustomerDto customer, Principal p) {

        return ResponseEntity.ok(customerService.postCustomer(customer, p));

        //return ResponseEntity.ok(new CustomerResponse("Customer Added Successfully", HttpStatus.CREATED));
    }

    @GetMapping("/get")
    private ResponseEntity<List<?>> getCustomer() {
        List customer = customerService.getCustomer();

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid  @RequestBody CustomerDto customer, Principal p) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer, p));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, Principal p) {
        return new ResponseEntity(customerService.changeStatus(id, p), HttpStatus.OK);

    }

    @DeleteMapping("/statusDelete/{id}")
    public ResponseEntity<CustomersResponse> changeStatusDelete(@PathVariable Long id, Principal p) {
         
         return response.response(customerService.changeStatusDelete(id, p), HttpStatus.OK);
        

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        CustomerDto customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

    @PostMapping("customerFamily/{id}")
    public CustomerResponse postCustomerFamily(@PathVariable Long id,@RequestBody List< CustomerFamilyDto> customerFamily, Principal p) {

        return customerService.postCustomerFamily(id, customerFamily, p);

    }

    @PutMapping("customerFamilyEdit/{id}")
    public ResponseEntity<?> updateCustomerFamily(@PathVariable Long id, @Valid @RequestBody List<@Valid CustomerFamilyDto> customer, Principal p) {
        return ResponseEntity.ok(customerService.updateCustomerFamily(id, customer, p));
    }

    @GetMapping("customerFamily/{id}")
    public ResponseEntity<List<?>> getcustomerFamily(@PathVariable Long id) {
        List customerFamily = customerService.getCustomerFamilyById(id);

        return new ResponseEntity<>(customerFamily, HttpStatus.OK);

    }

    @GetMapping("abc")
    public String namaste() {
        return "Namaste";
    }

}
