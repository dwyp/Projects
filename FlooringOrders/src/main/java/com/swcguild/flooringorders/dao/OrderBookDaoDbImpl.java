/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringorders.dao;

import com.swcguild.flooringorders.model.Order;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author daniel
 */
public class OrderBookDaoDbImpl implements OrderBookDao {

    // prepared statements for SQL DB
    private static final String SQL_INSERT_ORDER
            = "INSERT INTO Orders (customer_name, state, tax_rate, product_type, "
            + "area, cost_per_sq_ft, labor_cost_per_sq_ft, material_cost, labor_cost,"
            + "tax, total) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_REMOVE_ORDER
            = "DELETE FROM Orders WHERE order_id = ?";
    private static final String SQL_SELECT_ORDER
            = "SELECT * FROM Orders WHERE order_id = ?";
    private static final String SQL_UPDATE_ORDER
            = "UPDATE Orders SET customer_name=?, state=?, tax_rate=?, product_type=?, "
            + "area=?, cost_per_sq_ft=?, labor_cost_per_sq_ft=?,  material_cost=?,"
            + "labor_cost=?, tax=?, total=? WHERE order_id = ?";
    private static final String SQL_SELECT_ALL_ORDERS
            = "SELECT * FROM Orders";
    private static final String SQL_COUNT_ORDERS
            = "SELECT COUNT(*) FROM Orders";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addOrder(Order newOrder) {
        jdbcTemplate.update(SQL_INSERT_ORDER, newOrder.getCustomerName(), newOrder.getState(),
                newOrder.getTaxRate(), newOrder.getProductType(), newOrder.getArea(),
                newOrder.getCostPerSqFt(), newOrder.getLaborCostPerSqFt(),
                newOrder.getMaterialCost(), newOrder.getLaborCost(), newOrder.getTax(),
                newOrder.getTotal());
        newOrder.setOrderNumber(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }

    @Override
    public void removeOrder(Integer orderNumber) {
        jdbcTemplate.update(SQL_REMOVE_ORDER, orderNumber);
    }

    @Override
    public Order getOrderById(Integer orderNumber) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORDER, new OrderMapper(), orderNumber);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Boolean verifyOrderNumber(int orderNumber) {
        try {
            Order test = jdbcTemplate.queryForObject(SQL_SELECT_ORDER, new OrderMapper(), orderNumber);
            if (test != null) {
                return true;
            }
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return false;
    }

    @Override
    public Order[] getAllOrders() {
        try {
            List<Order> olist = jdbcTemplate.query(SQL_SELECT_ALL_ORDERS, new OrderMapper());
            return olist.toArray(new Order[0]);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public
            int getOrderBookSize() {
        return jdbcTemplate.queryForObject(SQL_COUNT_ORDERS, Integer.class
        );
    }

    @Override
    public void updateOrder(Order updatedOrder) {
        jdbcTemplate.update(SQL_UPDATE_ORDER, updatedOrder.getCustomerName(), updatedOrder.getState(),
                updatedOrder.getTaxRate(), updatedOrder.getProductType(), updatedOrder.getArea(),
                updatedOrder.getCostPerSqFt(), updatedOrder.getLaborCostPerSqFt(),
                updatedOrder.getMaterialCost(), updatedOrder.getLaborCost(), updatedOrder.getTax(),
                updatedOrder.getTotal(), updatedOrder.getOrderNumber());

    }

    private static final class OrderMapper implements ParameterizedRowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();

            order.setOrderNumber(rs.getInt("order_id"));
            order.setCustomerName(rs.getString("customer_name"));
            order.setState(rs.getString("state"));
            order.setTaxRate(rs.getFloat("tax_rate"));
            order.setProductType(rs.getString("product_type"));
            order.setArea(rs.getFloat("area"));
            
            BigDecimal costPerSqFt = new BigDecimal(String.valueOf(rs.getString("cost_per_sq_ft")));
            BigDecimal laborCostPerSqFt = new BigDecimal(String.valueOf(rs.getString("labor_cost_per_sq_ft")));
            BigDecimal materialCost = new BigDecimal(String.valueOf(rs.getString("material_cost")));
            BigDecimal laborCost = new BigDecimal(String.valueOf(rs.getString("labor_cost")));
            BigDecimal tax = new BigDecimal(String.valueOf(rs.getString("tax")));
            BigDecimal Total = new BigDecimal(String.valueOf(rs.getString("total")));
            
            order.setCostPerSqFt(costPerSqFt);
            order.setLaborCostPerSqFt(laborCostPerSqFt);
            order.setMaterialCost(materialCost);
            order.setLaborCost(laborCost);
            order.setTax(tax);
            order.setTotal(Total);

            return order;
        }

    }
}
