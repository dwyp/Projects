/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringorders.logic;

import com.swcguild.flooringorders.dao.SupportDao;
import com.swcguild.flooringorders.model.Order;
import java.math.BigDecimal;


/**
 *
 * @author daniel
 */
public class OrderFactory {
    public Order completeOrder(Order partialOrder, SupportDao support) {        
        
        // Retrieves appropriate values from files and sets values necessary to complete order
        partialOrder.setCostPerSqFt(support.getProductList().get(partialOrder.getProductType()).getCostPerSqFt());
        partialOrder.setLaborCostPerSqFt(support.getProductList().get(partialOrder.getProductType()).getLaborCostPerSqFt());
        support.getProductList().get(partialOrder.getProductType()).getCostPerSqFt();
        partialOrder.setMaterialCost(calcMaterial(partialOrder));
        partialOrder.setLaborCost(calcLabor(partialOrder));
        partialOrder.setTaxRate(support.getTaxList().get(partialOrder.getState()));
        partialOrder.setTax(calcTax(partialOrder));
        partialOrder.setTotal(calcTotal(partialOrder));
        
        return partialOrder;
    }
    
    public Order calcOrder(Order partialOrder) {
        partialOrder.setMaterialCost(calcMaterial(partialOrder));
        partialOrder.setLaborCost(calcLabor(partialOrder));
        partialOrder.setTax(calcTax(partialOrder));
        partialOrder.setTotal(calcTotal(partialOrder));
        
        return partialOrder;
    }

    
    //Methods that contain calculations that are necessary for completing it and are passed into the Order
    private BigDecimal calcMaterial(Order order) {
        BigDecimal area = new BigDecimal(String.valueOf(order.getArea()));
        return order.getCostPerSqFt().multiply(area);
    }

    private BigDecimal calcLabor(Order order) {
        BigDecimal area = new BigDecimal(String.valueOf(order.getArea()));
        return order.getLaborCostPerSqFt().multiply(area);
    }

    private BigDecimal calcTax(Order order) {
        BigDecimal taxRate = new BigDecimal(String.valueOf(order.getTaxRate()/100));
        return (order.getMaterialCost().add(order.getLaborCost()).multiply(taxRate));
    }

    private BigDecimal calcTotal(Order order) {
        return order.getLaborCost().add(order.getMaterialCost().add(order.getTax()));
    }
}

