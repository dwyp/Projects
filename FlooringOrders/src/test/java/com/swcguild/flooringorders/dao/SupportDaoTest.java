/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringorders.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author daniel
 */
public class SupportDaoTest {
    
    private SupportDao dao;
    
    public SupportDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (SupportDao) ctx.getBean("supportDao");
    
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of verifyState method, of class SupportDao.
     */
    @Test
    public void testVerifyState() {
        System.out.println("verifyState");
        assertTrue(dao.verifyState("OH"));
        assertFalse(dao.verifyState("magic"));
    }

    /**
     * Test of verifyProduct method, of class SupportDao.
     */
    @Test
    public void testVerifyProduct() {
        System.out.println("verifyProduct");
        assertTrue(dao.verifyProduct("carpet"));
        assertFalse(dao.verifyProduct("magic"));
    }

    /**
     * Test of getTaxList method, of class SupportDao.
     */
    @Test
    public void testGetTaxList() {
        System.out.println("getTaxList");
        assertEquals(dao.getTaxList().size(),4);
    }

    /**
     * Test of getProductList method, of class SupportDao.
     */
    @Test
    public void testGetProductList() {
        System.out.println("getProductList");
        assertEquals(dao.getProductList().size(),4);
    } 
}
