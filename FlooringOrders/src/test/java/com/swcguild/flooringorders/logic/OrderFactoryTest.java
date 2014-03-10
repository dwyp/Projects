/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringorders.logic;

import com.swcguild.flooringorders.dao.SupportDao;
import com.swcguild.flooringorders.model.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author daniel
 */
public class OrderFactoryTest {

    public OrderFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of completeOrder method, of class OrderFactory.
     */
    @Test
    public void testCompleteOrder() {
        System.out.println("completeOrder");
        OrderFactory myFactory = new OrderFactory();
        SupportDao mySupport;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        mySupport = (SupportDao) ctx.getBean("supportDao");

        Order order = new Order();
        order.setCustomerName("John");
        order.setProductType("Carpet");
        order.setState("OH");
        order.setArea(100);

        myFactory.completeOrder(order, mySupport);

        System.out.println(order.getTax());

//        assertEquals(order.getTaxRate(), 6.25, 0.01);
//        assertEquals(Integer.parseInt(order.getLaborCost().toString()), 210, 0.01);
//        assertEquals(Integer.parseInt(order.getMaterialCost().toString()), 225, 0.01);
    }

}
