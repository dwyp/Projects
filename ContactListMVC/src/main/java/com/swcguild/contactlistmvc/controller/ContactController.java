/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.controller;

import com.swcguild.contactlistmvc.dao.ContactListDao;
import com.swcguild.contactlistmvc.model.Contact;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */

@Controller
public class ContactController {
    
    private ContactListDao dao;
    
    @Inject
    public ContactController(ContactListDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value="/contactList", method=RequestMethod.GET)
    public String displayContactList(Map <String, Object> model) {
        Contact[] cArray = dao.getAllContacts();
        model.put("contactList", cArray);
        return "displayContactList";
    }
    
    @RequestMapping(value="/displayNewContactForm", method=RequestMethod.GET)
    public String displayNewContactForm(Map<String, Object> model) {
        return "addContactForm";
    }
    
    @RequestMapping(value="/addContact", method=RequestMethod.POST)
    public String addContact(Map<String, Object> model,
                             HttpServletRequest req,
                             HttpServletResponse res) {
        
       String name= req.getParameter("contactName");
       String phone = req.getParameter("contactPhone");
       String email = req.getParameter("contactEmail");
       Contact newContact = new Contact();
       newContact.setName(name);
       newContact.setPhone(phone);
       newContact.setEmail(email);
       dao.addContact(newContact);
        return "redirect:contactList";
    }
    
    @RequestMapping(value="/deleteContact", method=RequestMethod.GET)
    public String deleteContact(@RequestParam("contactId") String id,
                                Map<String, Object> model, Principal principal) {
        System.out.println(principal.getName());
        dao.removeContact(Integer.parseInt(id));
        return "redirect:contactList";
    }
    
    @RequestMapping(value="/editContactForm", method=RequestMethod.GET)
    public String displayEditContactForm(@RequestParam("contactId") String id,
                                         Map<String, Object> model) {
        Contact contact = dao.getContactById(Integer.parseInt(id));
        model.put("contact", contact);
        return "editContactForm";
    }
    
    @RequestMapping(value="/updateContact", method=RequestMethod.POST)
    public String updateContact(@Valid Contact contact, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "editContactForm";
        }
        
        dao.updateContact(contact);
        return "redirect:contactList";
    }
    
    @RequestMapping(value="/searchContactForm", method=RequestMethod.GET)
    public String displaySearchContactForm() {
        return "searchContactForm";
    }
    
    @RequestMapping(value="/searchContacts", method=RequestMethod.POST)
    public String searchContacts(HttpServletRequest req, Model model) {
        Map<String, String> criteria = new HashMap<>();
        if (!req.getParameter("contactName").isEmpty()) {
            criteria.put("name", req.getParameter("contactName"));
        }
        if (!req.getParameter("contactPhone").isEmpty()) {
            criteria.put("phone", req.getParameter("contactPhone"));
        }
        if (!req.getParameter("contactEmail").isEmpty()) {
            criteria.put("email", req.getParameter("contactEmail"));
        }
        
        Contact[] cArray = dao.searchContacts(criteria);
        model.addAttribute("contactList", cArray);
        return "displayContactList";
    }
    
}
