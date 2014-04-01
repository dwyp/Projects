/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Contact {
    private int contactId;
    @Size(min=2, max=50, message="Name must be between 2 and 50 characters in length.")
    private String name;
    @Pattern(regexp="[0-9]+", message="Phone Number must be all digits.") 
    @Size(min=10, max=10, message="Phone Number must be 10 digits.")
    private String phone;
    @NotEmpty @Email(message="Please provide a valid email address.")
    private String email;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
