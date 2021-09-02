/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Aswin
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_LOGIN")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="PASSWORD", length = 20)
    private String password;
    @Column(name="STATUS")
    private String Status;
    @Column(name = "USERNAME",unique = true, length = 20,nullable = false)
    private String username;
    @Column(name = "ATTEMPT_COUNT")
    private int attemptCount;

}
