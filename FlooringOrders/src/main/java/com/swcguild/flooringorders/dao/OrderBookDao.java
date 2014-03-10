/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringorders.dao;

import com.swcguild.flooringorders.model.Order;

/**
 *
 * @author daniel
 */
public interface OrderBookDao {

    public void addOrder(Order newOrder);

    public void removeOrder(Integer orderNumber);

    public Order getOrderById(Integer orderNumber);

    public Boolean verifyOrderNumber(int orderNumber);
    
    public Order[] getAllOrders();
    
    public int getOrderBookSize();
    
    public void updateOrder(Order updatedOrder);
}
