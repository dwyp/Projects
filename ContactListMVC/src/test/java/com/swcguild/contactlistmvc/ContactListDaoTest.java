/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.contactlistmvc;

import com.swcguild.contactlistmvc.dao.ContactListDao;
import com.swcguild.contactlistmvc.model.Contact;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class ContactListDaoTest {

    private ContactListDao dao;

    public ContactListDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Ask Spring for my DAO
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (ContactListDao) ctx.getBean("contactListDao");

        // Grab a JdbcTemplate to use for cleaning up
//        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        cleaner.execute("delete from contacts");
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//    @Test
//    public void addGetDeleteContact() {
//        // create new contact
//        Contact nc = new Contact();
//        nc.setName("John");
//        nc.setEmail("john@john.com");
//        nc.setPhone("1234445678");
//
//        dao.addContact(nc);
//
//        Contact fromDb = dao.getContactById(nc.getContactId());
//
//        assertEquals(fromDb.getContactId(), nc.getContactId());
//        assertEquals(fromDb.getName(), nc.getName());
//        assertEquals(fromDb.getPhone(), nc.getPhone());
//        assertEquals(fromDb.getEmail(), nc.getEmail());
//
//        dao.removeContact(nc.getContactId());
//
//        assertNull(dao.getContactById(nc.getContactId()));
//    }
//
//    @Test
//    public void addUpdateContact() {
//        // create new contact
//        Contact nc = new Contact();
//        nc.setName("Jimmy Smith");
//        nc.setEmail("jimmy@smith.com");
//        nc.setPhone("1112223333");
//
//        dao.addContact(nc);
//
//        nc.setPhone("9999999999");
//
//        dao.updateContact(nc);
//
//        Contact fromDb = dao.getContactById(nc.getContactId());
//
//        assertEquals(fromDb.getContactId(), nc.getContactId());
//        assertEquals(fromDb.getName(), nc.getName());
//        assertEquals(fromDb.getPhone(), nc.getPhone());
//        assertEquals(fromDb.getEmail(), nc.getEmail());
//    }
//
//    @Test
//    public void getAllContacts() {
//        // create new contact
//        Contact nc = new Contact();
//        nc.setName("Jimmy Smith");
//        nc.setEmail("jimmy@smith.com");
//        nc.setPhone("1112223333");
//
//        dao.addContact(nc);
//
//        // create new contact
//        Contact nc2 = new Contact();
//        nc2.setName("John Jones");
//        nc2.setEmail("john@jones.com");
//        nc2.setPhone("5556667777");
//
//        dao.addContact(nc);
// 
//        Contact[] cArr = dao.getAllContacts();
//        assertEquals(cArr.length, 2);
//    }
}
