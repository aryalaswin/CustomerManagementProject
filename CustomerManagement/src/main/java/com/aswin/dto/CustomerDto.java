/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.dto;

import com.aswin.validation.BeanValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Aswin
 */
@Component
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    @Pattern(regexp = BeanValidation.NAMEPATTERN, message = "Firstname only contains 3 to 15 alphabets without any space")
    private String firstName;

    @Pattern(regexp = ("^$|[a-zA-Z]{3,15}$"), message = "Middlename only contains 3 to 15 alphabets without any space")
    private String middleName;

    @Pattern(regexp = BeanValidation.NAMEPATTERN, message = "Lastname only contains 3to 15 alphabets without any space")
//    @Size(min = 3, max = 15, message = "Lastname " + com.aswin.validation.BeanValidation.MAXMIN)
    private String lastName;

    @NotBlank
    @Pattern(regexp = ("^M(ale)$|^F(emale)$"), message = "The string given to  Gender field is not valid")
    private String gender;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth is not valid")
    private java.time.LocalDate dateOfBirth;
    @Pattern(regexp = ("^|ACTIVE?$|INACTIVE?$"), message = "The string given to  Status field is not valid")
    private String status;

    @Pattern(regexp = BeanValidation.CITIZENSHIPVALIDATION, message = "Citizenship number is not valid")
    private String citizenshipNumber;
    @Pattern(regexp = BeanValidation.ADDRESSPATTERN, message = "Address is not valid")
    private String address;

    @Pattern(regexp = ("Married?$|Unmarried?$"), message = "The string given to  Marrital status field is not valid")
    private String maritalStatus;

    @Pattern(regexp = BeanValidation.EMAILVALIDATION, message = "Email address is not valid")
    private String emailAddress;
    @Pattern(regexp = "98+[0-9]{8}$", message = "Phone number is not valid")
    private String mobileNumber;

    private Long addedBy;
//
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date addedDate;

    private Long modifiedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

}
