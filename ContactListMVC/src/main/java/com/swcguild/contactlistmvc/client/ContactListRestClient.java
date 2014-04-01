/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.client;

import com.swcguild.contactlistmvc.model.Contact;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author apprentice
 */
public class ContactListRestClient {
    
    public static void main(String[] args) {
        ContactListRestClient client = new ContactListRestClient();
        Contact contact = new Contact();
        contact.setName("FROM CLIENT");
        contact.setPhone("11122234");
        contact.setEmail("email");
        contact = client.createContact(contact);
        System.out.println(contact.getContactId());
      
        Contact[] contacts = client.getAllContacts();
        for (Contact currentCon : contacts) {
            System.out.println(currentCon.getName());
        } 
        
        
    }
    
    public @ResponseBody Contact[] getAllContacts() {
        RestTemplate rt = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJacksonHttpMessageConverter());
        rt.setMessageConverters(converters);
        Contact[] contacts = rt.getForObject("http://localhost:8080/ContactListMVC/spring/rest/contacts", Contact[].class);
        return contacts;
    }
    
    public @ResponseBody Contact createContact(Contact contact) {
        RestTemplate rt = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJacksonHttpMessageConverter());
        rt.setMessageConverters(converters);
        return rt.postForObject("http://localhost:8080/ContactListMVC/spring/rest/contact", contact, Contact.class);
        
    }
    
    
    
}
