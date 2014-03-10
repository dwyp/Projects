/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringorders.dao;

import com.swcguild.flooringorders.model.Product;
import java.util.HashMap;

/**
 *
 * @author apprentice
 */
public interface SupportDao {
    
    public Boolean verifyState(String state);
    
    public Boolean verifyProduct(String productType);
    
    public HashMap<String, Float> getTaxList();
    
    public HashMap<String, Product> getProductList();
    
    
    
}
