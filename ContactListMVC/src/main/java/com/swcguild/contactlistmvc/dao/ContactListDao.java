/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.dao;

import com.swcguild.contactlistmvc.model.Contact;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface ContactListDao {
    
    public Contact addContact(Contact contact);
    
    public void removeContact(int contactId);
    
    public void updateContact(Contact contact);
    
    public Contact[] getAllContacts();
    
    public Contact getContactById(int contactId);
    
    public Contact[] searchContacts(Map<String, String> criteria);
    
}
