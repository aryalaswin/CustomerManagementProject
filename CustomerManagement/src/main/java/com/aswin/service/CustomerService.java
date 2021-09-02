/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.service;

import com.aswin.dto.CustomerDto;
import com.aswin.dto.CustomerFamilyDto;
import com.aswin.response.CustomerResponse;
import com.aswin.response.CustomersResponse;
import java.security.Principal;
import java.util.List;

/**
 *
 * @author Aswin
 */

public interface CustomerService {

    List<?> getCustomer();

    CustomerResponse postCustomer(CustomerDto customer, Principal p);

    CustomerResponse updateCustomer(Long id, CustomerDto customer, Principal p);

    CustomerResponse changeStatus(Long id, Principal p);

    String changeStatusDelete(Long id, Principal p);

    CustomerDto getCustomerById(Long id);

    public CustomerResponse postCustomerFamily(Long id, List<CustomerFamilyDto> customerFamily,Principal p);
     public CustomerResponse updateCustomerFamily(Long id, List<CustomerFamilyDto> customerFamily,Principal p);

 

    public List getCustomerFamilyById(Long id);
}
