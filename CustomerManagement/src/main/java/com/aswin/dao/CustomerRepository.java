package com.aswin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aswin.entity.Customer;
import com.aswin.entity.CustomerFamily;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByEmailAddress(String emailAddress);
    @Query(value = "SELECT * FROM CUSTOMER_TABLE c WHERE c.status = 'ACTIVE' || c.status= 'INACTIVE'", nativeQuery = true)
    List<Customer> findAllCustomers();
    List<CustomerFamily> findByFamily(Customer cus);
    
    
//      @Query(value = "SELECT EMAIL_ADDRESS from CUSTOMER_TABLE c where c.EMAIL_ADDRESS = :emailAddress && (c.EMAIL_ADDRESS!=null||c.EMAIL_ADDRESS!='')", nativeQuery = true)
//      public String checkEmailAddress(@Param("emailAddress")String emailAddress);
}
