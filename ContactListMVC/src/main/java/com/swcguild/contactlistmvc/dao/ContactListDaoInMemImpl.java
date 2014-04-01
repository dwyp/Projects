/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.dao;

import com.swcguild.contactlistmvc.model.Contact;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class ContactListDaoInMemImpl implements ContactListDao {
    private Map<Integer, Contact> contactMap = new HashMap<>();
    private static int contactIdCounter = 0;

    public Contact addContact(Contact contact) {
        contact.setContactId(contactIdCounter);
        contactIdCounter++;
        contactMap.put(contact.getContactId(), contact);
        return contact;
    }

    public void removeContact(int contactId) {
        contactMap.remove(contactId);
    }

    public void updateContact(Contact contact) {
        contactMap.put(contact.getContactId(), contact);
    }

    public Contact[] getAllContacts() {
        Collection<Contact> c = contactMap.values();
        return c.toArray(new Contact[0]);
    }

    public Contact getContactById(int contactId) {
        return contactMap.get(contactId);
    }

    @Override
    public Contact[] searchContacts(Map<String, String> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
