/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aswin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_family_details")
public class CustomerFamily implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
   
    @Column(name = "RELATIONSHIP",nullable=false, length = 20)
    private String relationship;
    @Column(name = "RELATION_PERSON_NAME",nullable=false, length = 20)
    private String relationPersonName;

}
