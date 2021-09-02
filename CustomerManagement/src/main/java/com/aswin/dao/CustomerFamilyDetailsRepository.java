package com.aswin.dao;

import com.aswin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aswin.entity.CustomerFamily;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFamilyDetailsRepository extends JpaRepository<CustomerFamily, Long> {

    public List<CustomerFamily> findByCustomer(Customer cust);

    @Query(value = "SELECT * FROM CUSTOMER_FAMILY_DETAILS c WHERE c.customer_id = :id", nativeQuery = true)
    List findCustomerFamilyById(@Param("id") Long id);

    @Query(value = "SELECT RELATION_PERSON_NAME from CUSTOMER_FAMILY_DETAILS c where c.customer_id = :id", nativeQuery = true)
      public List checkRelationShip(@Param("id") Long id);

  
}
