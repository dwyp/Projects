/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author apprentice
 */
public class NewClass {
    
    public static void main(String[] args) {
//        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//        String enc = encoder.encodePassword("password", "test");
//        System.out.println(enc);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String enc = encoder.encode("password");
        
        
        //boolean matches = encoder.matches("password", "$2a$10$U8pw1LFbu3YMTtc9e30EIey8Vih9/5HRSOoB2eNwA7AchWfcbKXDS");
        
        //System.out.println(matches);
        System.out.println(enc);
        
    }
    
}
