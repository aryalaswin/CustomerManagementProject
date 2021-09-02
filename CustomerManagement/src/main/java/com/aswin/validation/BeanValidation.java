/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aswin.validation;

/**
 *
 * @author Aswin
 */
public class BeanValidation {

    static public final String NAMEPATTERN = "^[a-zA-Z]{3,15}$";
    static public final String RELATIONPATTERN = "[a-zA-Z]{3,15}(?: +[a-zA-Z]{3,10}){0,3}$";
    static public final String ADDRESSPATTERN = "[a-zA-Z]{3,15}(?:[\\,]{0,1} +[a-zA-Z]{3,10}){0,3}$";
    static public final String EMAILVALIDATION = "^$|[\\w-\\.]{1,10}@([\\w-]{2,10}\\.)+[\\w-]{2,4}$";
    static public final String CITIZENSHIPVALIDATION="^[0-9]{2,10}(?:[\\-\\/][0-9]{2,5}){0,3}$";

    static public final String BLANKMESSAGE = "field cannot be blank";
   
}
