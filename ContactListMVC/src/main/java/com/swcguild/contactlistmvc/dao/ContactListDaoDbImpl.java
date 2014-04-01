/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.contactlistmvc.dao;

import com.swcguild.contactlistmvc.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class ContactListDaoDbImpl implements ContactListDao {

    private static final String SQL_INSERT_CONTACT
            = "insert into contacts (name, phone, email) values (?, ?, ?)";
    private static final String SQL_DELETE_CONTACT
            = "delete from contacts where contact_id = ?";
    private static final String SQL_SELECT_CONTACT
            = "select * from contacts where contact_id = ?";
    private static final String SQL_UPDATE_CONTACT
            = "update contacts set name = ?, phone = ?, email = ? where contact_id = ?";
    private static final String SQL_SELECT_ALL_CONTACTS
            = "select * from contacts";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Contact addContact(Contact contact) {
        jdbcTemplate.update(SQL_INSERT_CONTACT, contact.getName(), contact.getPhone(), contact.getEmail());
        contact.setContactId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return contact;
    }

    @Override
    public void removeContact(int contactId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT, contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT,
                contact.getName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getContactId());
    }

    @Override
    public Contact[] getAllContacts() {
        List<Contact> cList = jdbcTemplate.query(SQL_SELECT_ALL_CONTACTS, new ContactMapper());
        return cList.toArray(new Contact[0]);
    }

    @Override
    public Contact getContactById(int contactId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CONTACT, new ContactMapper(), contactId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just want to
            // return null in this case
            return null;
        }
    }

    private static final class ContactMapper implements ParameterizedRowMapper<Contact> {

        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contact contact = new Contact();
            contact.setContactId(rs.getInt("contact_id"));
            contact.setName(rs.getString("name"));
            contact.setPhone(rs.getString("phone"));
            contact.setEmail(rs.getString("email"));
            return contact;
        }
    }

    public Contact[] searchContacts(Map<String, String> criteria) {
        if (criteria.size() == 0) {
            return getAllContacts();
        } else {

            StringBuilder sQuery = new StringBuilder("select * from contacts where ");

            // build the where clause
            int numParams = criteria.size();
            int paramPosition = 0;
        // we'll put the positional parameters into an array, the order of the
            // parameters will match the order in whic we get the search criteria
            // from the map
            String[] paramVals = new String[numParams];
            Set<String> keySet = criteria.keySet();
            Iterator<String> iter = keySet.iterator();
            // build up the where clause based on the key/value pairs in the map
            while (iter.hasNext()) {
                String currentKey = iter.next();
            // if we are not the first one in, we must add an AND to the 
                // where clause
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                // now append our criteria name
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
            // grab the value for this search criteria and put it into the 
                // paramVals array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            List<Contact> cList = jdbcTemplate.query(sQuery.toString(), new ContactMapper(), paramVals);
            return cList.toArray(new Contact[0]);
        }
    }

}
