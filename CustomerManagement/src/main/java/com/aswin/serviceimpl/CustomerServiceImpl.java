/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.serviceimpl;

import com.aswin.exception.ResourceNotFoundException;
import com.aswin.dao.CustomerFamilyDetailsRepository;
import com.aswin.dao.CustomerRepository;
import com.aswin.dao.LoginRepository;
import com.aswin.dto.CustomerDto;
import com.aswin.dto.CustomerFamilyDto;
import com.aswin.entity.Customer;
import com.aswin.entity.CustomerFamily;
import com.aswin.entity.User;
import com.aswin.exception.CustomerNotActiveException;
import com.aswin.exception.EditUnsuccessException;
import com.aswin.response.CustomerResponse;
import com.aswin.response.CustomersResponse;
import com.aswin.service.CustomerService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aswin
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    Customer cust;
    @Autowired
    CustomerDto cDto;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerFamilyDetailsRepository customerfamilyRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    CustomerFamilyDto cfDto;
    @Autowired
    LoginRepository loginRepo;
    @Autowired
    CustomersResponse response;

    @Override
    public List<Customer> getCustomer() {

        return customerRepository.findAllCustomers();
    }

    @Override
    public CustomerResponse postCustomer(CustomerDto customer, Principal p) {
        User user = loginRepo.findByUsername(p.getName());
        // Customer customerCheckEmail = customerRepository.findByEmailAddress(customer.getEmailAddress());
        //  String emaString=customerRepository.checkEmailAddress(customer.getEmailAddress());

        if (!customer.getEmailAddress().isEmpty()) {
            Customer customerCheckEmail = customerRepository.findByEmailAddress(customer.getEmailAddress());

            if (customerCheckEmail != null) {
                throw new DataIntegrityViolationException("The given email address already present in database.");
            }
        }
        cust = mapper.map(customer, Customer.class);
//        cust.setFirstName(customer.getFirstName());
//        cust.setMiddleName(customer.getMiddleName());
//        cust.setLastName(customer.getLastName());
//        cust.setAddress(customer.getAddress());
//        cust.setCitizenshipNumber(customer.getCitizenshipNumber());
//        cust.setDateOfBirth(customer.getDateOfBirth());
//        cust.setGender(customer.getGender());
//        cust.setMaritalStatus(customer.getMaritalStatus());
//        cust.setStatus(customer.getStatus());
//        cust.setEmailAddress(customer.getEmailAddress());
//        cust.setMobileNumber(customer.getMobileNumber());

        cust.setStatus("ACTIVE");
        cust.setAddedBy(user.getId());
        cust.setAddedDate(new Date());

//       
        customerRepository.save(cust);

        return new CustomerResponse("Customer Added Successfully");

    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerDto customer, Principal p) {
        cust = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Exist By Id" + id));
        if (cust.getStatus().equalsIgnoreCase("ACTIVE")) {

            if (!((cust.getFirstName().equals(customer.getFirstName())) && (cust.getMiddleName().equals(customer.getMiddleName())) && (cust.getLastName().equals(customer.getLastName())) && (cust.getAddress().equals(customer.getAddress())) && (cust.getCitizenshipNumber().equals(customer.getCitizenshipNumber())) && (cust.getEmailAddress().equals(customer.getEmailAddress())) && (cust.getGender().equals(customer.getGender())) && (cust.getMaritalStatus().equals(customer.getMaritalStatus())) && (cust.getMobileNumber().equals(customer.getMobileNumber())) && (cust.getDateOfBirth().equals(customer.getDateOfBirth())))) {
                if (!customer.getEmailAddress().isEmpty()) {
                    Customer customerCheckEmail = customerRepository.findByEmailAddress(customer.getEmailAddress());
                    if (customerCheckEmail != null && !(cust.getEmailAddress().equalsIgnoreCase(customerCheckEmail.getEmailAddress()))) {
                        throw new DataIntegrityViolationException("Email address already present");
                    }
                }
                User user = loginRepo.findByUsername(p.getName());
                cust.setFirstName(customer.getFirstName());
                cust.setMiddleName(customer.getMiddleName());
                cust.setLastName(customer.getLastName());
                cust.setAddress(customer.getAddress());
                cust.setCitizenshipNumber(customer.getCitizenshipNumber());
                cust.setDateOfBirth(customer.getDateOfBirth());
                cust.setGender(customer.getGender());
                cust.setMaritalStatus(customer.getMaritalStatus());
                cust.setEmailAddress(customer.getEmailAddress());
                cust.setMobileNumber(customer.getMobileNumber());
                cust.setModifiedDate(new Date());
                cust.setModifiedBy(user.getId());
                customerRepository.save(cust);
                return new CustomerResponse("Customer "+cust.getFirstName()+ "is pdated Successfully");
            } else {
                throw new EditUnsuccessException("Please change at least one field", HttpStatus.CONFLICT);
            }
        } else {
            throw new CustomerNotActiveException("Customer is not Active", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public CustomerResponse changeStatus(Long id, Principal p) {
        User user = loginRepo.findByUsername(p.getName());
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Exist By Id" + id));
        String status = customer.getStatus();
        if (status.equalsIgnoreCase("ACTIVE")) {
            customer.setStatus("INACTIVE");
        } else {
            customer.setStatus("ACTIVE");
        }
        customer.setModifiedBy(user.getId());
        customer.setModifiedDate(new Date());
        customerRepository.save(customer);
        return new CustomerResponse("Status of "+ customer.getFirstName()+ " is changed successfully ");
    }

    @Override
    public String changeStatusDelete(Long id, Principal p) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Exist By Id" + id));
         String status = customer.getStatus();
        if (status.equalsIgnoreCase("ACTIVE")){
        customer.setStatus("DELETED");
        User user = loginRepo.findByUsername(p.getName());
        customer.setModifiedBy(user.getId());
        customer.setModifiedDate(new Date());
        customerRepository.save(customer);
        return "Customer "+customer.getFirstName()+" is deleted Successfully";
    }else{
                   throw new CustomerNotActiveException("Customer is not Active", HttpStatus.BAD_REQUEST);
     
        }}

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Exist By Id" + id));
        cDto = mapper.map(customer, CustomerDto.class);
        return cDto;
    }

    @Override
    public CustomerResponse postCustomerFamily(Long id,  List<CustomerFamilyDto> customer, Principal p) {

        Customer cust = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Exist By Id" + id));

        if (cust.getStatus().equalsIgnoreCase("ACTIVE")) {
            long custid = cust.getId();
            List c = customerfamilyRepository.findCustomerFamilyById(custid);
            if (c.size() == 0) {
                for (CustomerFamilyDto cus : customer) {
                    CustomerFamily customerFamily = new CustomerFamily();
                    customerFamily.setCustomer(cust);
                    customerFamily.setRelationship(cus.getRelationship());
                    customerFamily.setRelationPersonName(cus.getRelationPersonName());
                    customerfamilyRepository.save(customerFamily);
                }
                User user = loginRepo.findByUsername(p.getName());
                cust.setModifiedBy(user.getId());
                cust.setModifiedDate(new Date());
                customerRepository.save(cust);
                return new CustomerResponse("The customer Family is Added Successfully!");

            }
            throw new EditUnsuccessException("The Family member of "+cust.getFirstName()+" is already exist",HttpStatus.CONFLICT);
        } else {
            throw new CustomerNotActiveException("Customer is not Active", HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public List<CustomerFamily> getCustomerFamilyById(Long id) {
        Customer cust = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Exist By Id" + id));
        return customerfamilyRepository.findByCustomer(cust);
    }

    @Override
    public CustomerResponse updateCustomerFamily(Long id, List<CustomerFamilyDto> customerFamily, Principal p) {
        Customer cust = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Exist By Id" + id));
        if (cust.getStatus().equalsIgnoreCase("ACTIVE")) {
          
            List cusRelationShip = customerfamilyRepository.checkRelationShip(cust.getId());
            List relationPersonName = new ArrayList<>();
            for (CustomerFamilyDto cusF : customerFamily) {
                cusF.getRelationPersonName();
                relationPersonName.add(cusF.getRelationPersonName());
            }
            if (!relationPersonName.equals(cusRelationShip)) {

                List<CustomerFamily> cFamily = cust.getFamily();

                if (cFamily.size() == customerFamily.size()) {
                    for (int i = 0; i < customerFamily.size(); i++) {
                        cFamily.get(i).setRelationship(customerFamily.get(i).getRelationship());
                        cFamily.get(i).setRelationPersonName(customerFamily.get(i).getRelationPersonName());
                        customerfamilyRepository.save(cFamily.get(i));
                    }
                } else if (cFamily.size() > customerFamily.size()) {
                    for (int i = 0; i < customerFamily.size(); i++) {
                        cFamily.get(i).setRelationship(customerFamily.get(i).getRelationship());
                        cFamily.get(i).setRelationPersonName(customerFamily.get(i).getRelationPersonName());
                        customerfamilyRepository.save(cFamily.get(i));
                    }
                    cFamily.remove(3);
                    customerfamilyRepository.saveAll(cFamily);
                } else if (cFamily.size() < customerFamily.size()) {
                    for (int i = 0; i < cFamily.size(); i++) {
                        cFamily.get(i).setRelationship(customerFamily.get(i).getRelationship());
                        cFamily.get(i).setRelationPersonName(customerFamily.get(i).getRelationPersonName());
                        customerfamilyRepository.save(cFamily.get(i));
                    }
                    cFamily = customerFamily
                            .stream()
                            .map(mapCustomerFamily -> mapper.map(mapCustomerFamily, CustomerFamily.class))
                            .collect(Collectors.toList());
                    cFamily.forEach(a -> {
                        a.setCustomer(cust);
                    });
                    customerfamilyRepository.save(cFamily.get(3));
                }

                User user = loginRepo.findByUsername(p.getName());
                cust.setModifiedBy(user.getId());
                cust.setModifiedDate(new Date());
                customerRepository.save(cust);
                return new CustomerResponse("The Familymembers Updated  Successfully!");
            } else {
                throw new EditUnsuccessException("Please Change at least one field", HttpStatus.CONFLICT);

            }
        } else {
            throw new CustomerNotActiveException("Customer is not Active", HttpStatus.BAD_REQUEST);
        }

    }
}
