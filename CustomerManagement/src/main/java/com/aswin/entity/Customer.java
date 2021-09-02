package com.aswin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER_TABLE")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
  
    @Column(name = "MIDDLE_NAME", nullable = true, length = 50)

    private String middleName;
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "GENDER", nullable = false, length = 50)
    private String gender;

   
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "STATUS", nullable = false, length = 10)
    private String status;

    @Column(name = "CITIZENSHIP_NO", nullable = false,length = 20)
    private String citizenshipNumber;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Column(name = "MARITAL_STATUS", nullable = false, length = 15)
    private String maritalStatus;

    @Column(name = "EMAIL_ADDRESS",length = 100)
    
    private String emailAddress;

    @Column(name = "MOBILE_NUMBER", nullable = false,length = 30)
    private String mobileNumber;

    @Column(name = "ADDED_BY")
    private Long addedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "ADDED_DATE", nullable = false)
    private Date addedDate;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CustomerFamily> family = new ArrayList<>();

}
