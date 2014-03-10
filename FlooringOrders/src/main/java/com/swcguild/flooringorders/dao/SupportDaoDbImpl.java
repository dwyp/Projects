/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringorders.dao;

import com.swcguild.flooringorders.model.Product;
import com.swcguild.flooringorders.model.Taxes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class SupportDaoDbImpl implements SupportDao {

    private static final String SQL_SELECT_TAXES
            = "SELECT * FROM Taxes WHERE state = ?";
    
    private static final String SQL_SELECT_PRODUCTS
            = "SELECT * FROM Products WHERE product_type = ?";
    
    private static final String SQL_SELECT_ALL_TAXES
            = "SELECT * FROM Taxes";
    
    private static final String SQL_SELECT_ALL_PRODUCTS
            = "SELECT * FROM Products";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    @Override
    public Boolean verifyState(String state) {
        try {
            Taxes test = jdbcTemplate.queryForObject(SQL_SELECT_TAXES, new TaxesMapper(), state);
            if (test != null) {
                return true;
            }
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean verifyProduct(String productType) {
        try {
            Product test = jdbcTemplate.queryForObject(SQL_SELECT_PRODUCTS, new ProductsMapper(), productType);
            if (test != null) {
                return true;
            }
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return false;
    }

    @Override
    public HashMap<String, Float> getTaxList() {

        try {
            List<Taxes> tlist = jdbcTemplate.query(SQL_SELECT_ALL_TAXES, new TaxesMapper());
            HashMap<String, Float> tMap = new HashMap<>();
            for(Taxes e:tlist) {
                tMap.put(e.getState(),e.getTaxRate());
            }
            
            return tMap;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
        
    }

    @Override
    public HashMap<String, Product> getProductList() {
        try {
            List<Product> plist = jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS, new ProductsMapper());
            HashMap<String, Product> pMap = new HashMap<>();
            for (Product e : plist) {
                pMap.put(e.getProductType(), e);
            }

            return pMap;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class TaxesMapper implements ParameterizedRowMapper<Taxes> {

        @Override
        public Taxes mapRow(ResultSet rs, int i) throws SQLException {

            Taxes taxes = new Taxes();

            taxes.setState(rs.getString("state"));
            taxes.setTaxRate(rs.getFloat("tax_rate"));

            return taxes;

        }

    }
    
    private static final class ProductsMapper implements ParameterizedRowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {

            Product product = new Product();

            product.setProductType(rs.getString("product_type"));
            
            BigDecimal costPerSqFt = new BigDecimal(String.valueOf(rs.getString("cost_per_sq_ft")));
            BigDecimal laborCostPerSqFt = new BigDecimal(String.valueOf(rs.getString("labor_cost_per_sq_ft")));
            
            product.setCostPerSqFt(costPerSqFt);
            product.setLaborCostPerSqFt(laborCostPerSqFt);

            return product;

        }

    }

}