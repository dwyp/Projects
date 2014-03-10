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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author daniel
 */
public class OrderBookDaoTest {
    
    private OrderBookDao dao;
    
    public OrderBookDaoTest() {
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
        dao = (OrderBookDao) ctx.getBean("orderBookDao");
        
        // get and clean database
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from Orders");
        
    }
    
    @After
    public void tearDown() {
    }
}
    /**
     * Test of addOrder removeOrder and getOrderById methods, of class OrderBookDao.
     */
//    @Test
//    public void testAddRemoveGetOrder() {
//        System.out.println("addOrder");
//        Order order = new Order();
//        
//        order.setCustomerName("John");
//        order.setState("OH");
//        order.setTaxRate(10);
//        order.setProductType("carpet");
//        order.setArea(100);
//        order.setCostPerSqFt(10);
//        order.setLaborCostPerSqFt(20);
//        order.setMaterialCost(1000);
//        order.setLaborCost(2000);
//        order.setTax(300);
//        order.setTotal(3300);
//        
//        dao.addOrder(order);
//        
//        Order testOrder = new Order();
//        testOrder = dao.getOrderById(order.getOrderNumber());
//        
//        assertEquals(testOrder.getCustomerName(),"John");
//        assertEquals(testOrder.getState(),"OH");
//        assertEquals(testOrder.getTaxRate(),10,0.1);
//        assertEquals(testOrder.getProductType(),"carpet");
//        assertEquals(testOrder.getArea(),100,0.1);
//        assertEquals(testOrder.getCostPerSqFt(),10,0.1);
//        assertEquals(testOrder.getLaborCostPerSqFt(),20,0.1);
//        assertEquals(testOrder.getMaterialCost(),1000,0.1);
//        assertEquals(testOrder.getLaborCost(),2000,0.1);
//        assertEquals(testOrder.getTax(),300,0.1);
//        assertEquals(testOrder.getTotal(),3300,0.1);
//        
//        dao.removeOrder(order.getOrderNumber());
//        
//        testOrder = dao.getOrderById(order.getOrderNumber());
//        
//        assertNull(testOrder);
//    }
//    
//    /**
//     * Test of verifyOrderNumber method, of class OrderBookDao.
//     */
//    @Test
//    public void testVerifyOrderNumber() {
//        System.out.println("verifyOrderNumber");
//        int orderNumber = 100;
//        
//        Boolean result = dao.verifyOrderNumber(orderNumber);
//        
//        assertFalse(result);
//        
//        Order order = new Order();
//        
//        order.setCustomerName("John");
//        order.setState("OH");
//        order.setTaxRate(10);
//        order.setProductType("carpet");
//        order.setArea(100);
//        order.setCostPerSqFt(10);
//        order.setLaborCostPerSqFt(20);
//        order.setMaterialCost(1000);
//        order.setLaborCost(2000);
//        order.setTax(300);
//        order.setTotal(3300);
//        
//        dao.addOrder(order);
//        
//        result = dao.verifyOrderNumber(order.getOrderNumber());
//        assertTrue(result);
//    }
//
//    /**
//     * Test of getAllOrders method, of class OrderBookDao.
//     */
//    @Test
//    public void testGetAllOrders() {
//        System.out.println("getAllOrders");
//        Order[] orders = new Order[2];
//        Order order = new Order();
//        
//        order.setCustomerName("John");
//        order.setState("OH");
//        order.setTaxRate(10);
//        order.setProductType("carpet");
//        order.setArea(100);
//        order.setCostPerSqFt(10);
//        order.setLaborCostPerSqFt(20);
//        order.setMaterialCost(1000);
//        order.setLaborCost(2000);
//        order.setTax(300);
//        order.setTotal(3300);
//        
//        dao.addOrder(order);
//        orders[0] = order;
//
//        order = new Order();
//        
//        order.setCustomerName("Jane");
//        order.setState("MI");
//        order.setTaxRate(20);
//        order.setProductType("tile");
//        order.setArea(200);
//        order.setCostPerSqFt(20);
//        order.setLaborCostPerSqFt(30);
//        order.setMaterialCost(2000);
//        order.setLaborCost(3000);
//        order.setTax(400);
//        order.setTotal(4300);
//        
//        dao.addOrder(order);
//        orders[1] = order;
//        
//        Order[] dbOrders = new Order[dao.getOrderBookSize()];
//        dbOrders = dao.getAllOrders();
//        
//        assertEquals(orders[0].getCustomerName(),dbOrders[0].getCustomerName());
//        assertEquals(orders[0].getState(),dbOrders[0].getState());
//        assertEquals(orders[0].getTaxRate(),dbOrders[0].getTaxRate(),0.1);
//        assertEquals(orders[0].getProductType(),dbOrders[0].getProductType());
//        assertEquals(orders[0].getArea(),dbOrders[0].getArea(),0.1);
//        assertEquals(orders[0].getCostPerSqFt(),dbOrders[0].getCostPerSqFt(),0.1);
//        assertEquals(orders[0].getLaborCostPerSqFt(),dbOrders[0].getLaborCostPerSqFt(),0.1);
//        assertEquals(orders[0].getMaterialCost(),dbOrders[0].getMaterialCost(),0.1);
//        assertEquals(orders[0].getLaborCost(),dbOrders[0].getLaborCost(),0.1);
//        assertEquals(orders[0].getTax(),dbOrders[0].getTax(),0.1);
//        assertEquals(orders[0].getTotal(),dbOrders[0].getTotal(),0.1);
//        
//        
//    }
//
//    /**
//     * Test of getOrderBookSize method, of class OrderBookDao.
//     */
//    @Test
//    public void testGetOrderBookSize() {
//        System.out.println("getOrderBookSize");
//
//        int expResult = 0;
//        int result = dao.getOrderBookSize();
//        assertEquals(expResult, result);
//          
//        Order order = new Order();
//        
//        order.setCustomerName("John");
//        order.setState("OH");
//        order.setTaxRate(10);
//        order.setProductType("carpet");
//        order.setArea(100);
//        order.setCostPerSqFt(10);
//        order.setLaborCostPerSqFt(20);
//        order.setMaterialCost(1000);
//        order.setLaborCost(2000);
//        order.setTax(300);
//        order.setTotal(3300);
//        
//        dao.addOrder(order);
//        
//        expResult = 1;
//        result = dao.getOrderBookSize();
//        
//        assertEquals(expResult,result);
//                
//    }
//
//    /**
//     * Test of updateOrder method, of class OrderBookDao.
//     */
//    @Test
//    public void testUpdateOrder() {
//        System.out.println("updateOrder");
//          
//        Order order = new Order();
//        
//        order.setCustomerName("John");
//        order.setState("OH");
//        order.setTaxRate(10);
//        order.setProductType("carpet");
//        order.setArea(100);
//        order.setCostPerSqFt(10);
//        order.setLaborCostPerSqFt(20);
//        order.setMaterialCost(1000);
//        order.setLaborCost(2000);
//        order.setTax(300);
//        order.setTotal(3300);
//        
//        dao.addOrder(order);
//        
//        order.setCustomerName("Jane");
//        order.setState("MI");
//        order.setTaxRate(20);
//        order.setProductType("tile");
//        order.setArea(200);
//        order.setCostPerSqFt(20);
//        order.setLaborCostPerSqFt(30);
//        order.setMaterialCost(2000);
//        order.setLaborCost(3000);
//        order.setTax(400);
//        order.setTotal(4300);
//        
//        dao.updateOrder(order);
//        
//        assertEquals(order.getCustomerName(),"Jane");
//        assertEquals(order.getState(),"MI");
//        assertEquals(order.getTaxRate(),20,0.1);
//        assertEquals(order.getProductType(),"tile");
//        assertEquals(order.getArea(),200,0.1);
//        assertEquals(order.getCostPerSqFt(),20,0.1);
//        assertEquals(order.getLaborCostPerSqFt(),30,0.1);
//        assertEquals(order.getMaterialCost(),2000,0.1);
//        assertEquals(order.getLaborCost(),3000,0.1);
//        assertEquals(order.getTax(),400,0.1);
//        assertEquals(order.getTotal(),4300,0.1);
//    }
// }
