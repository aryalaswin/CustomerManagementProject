/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.dto;

import com.aswin.validation.BeanValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerFamilyDto {

    private Long id;
    @JsonIgnore
   
    private CustomerDto customer;
@NotNull
    @Pattern(regexp = ("^Father$|^Mother$|^Grandfather$|^Spouse$"), message = "Relationship is not valid")
    private String relationship;
@NotNull
    @Pattern(regexp = BeanValidation.RELATIONPATTERN, message = "Relationperson name is not valid")
    private String relationPersonName;

}
