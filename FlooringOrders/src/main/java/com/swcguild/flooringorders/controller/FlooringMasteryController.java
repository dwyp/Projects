package com.swcguild.flooringorders.controller;

import com.swcguild.flooringorders.dao.OrderBookDao;
import com.swcguild.flooringorders.dao.SupportDao;
import com.swcguild.flooringorders.logic.OrderFactory;
import com.swcguild.flooringorders.model.Order;
import com.swcguild.flooringorders.model.Product;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlooringMasteryController {

    private OrderBookDao dao;
    private SupportDao mySupport;
    private OrderFactory myOrderFactory = new OrderFactory();

    @Inject
    public FlooringMasteryController(OrderBookDao dao, SupportDao mySupport) {
        this.dao = dao;
        this.mySupport = mySupport;
    }

    @RequestMapping(value = {"/","/displayOrders"}, method = RequestMethod.GET)
    public String displayOrders(Map<String, Object> model) {
        Order[] orders = dao.getAllOrders();
        int size = dao.getOrderBookSize();

        Product[] products = new Product[mySupport.getProductList().size()];
        products = mySupport.getProductList().values().toArray(products);

        String[] states = new String[mySupport.getTaxList().size()];
        states = mySupport.getTaxList().keySet().toArray(states);

        model.put("productTypes", products);
        model.put("states", states);

        model.put("size", size);
        model.put("orders", orders);

        return "displayOrdersView";
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String addOrder(Map<String, Object> model,
            HttpServletRequest req,
            HttpServletResponse res) {

        String name = req.getParameter("customerName");
        String state = req.getParameter("state");
        String product = req.getParameter("productType");
        String area = req.getParameter("area");

        Order order = new Order();
        order.setCustomerName(name);
        order.setState(state);
        order.setProductType(product);
        order.setArea(Float.parseFloat(area));

        order = myOrderFactory.completeOrder(order, mySupport);
        dao.addOrder(order);

        return "redirect: displayOrders";
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public String deleteOrder(Map<String, Object> model,
            HttpServletRequest req,
            HttpServletResponse res) {
        
        dao.removeOrder(Integer.parseInt(req.getParameter("id")));

        return "redirect: displayOrders";
    }

    @RequestMapping(value = "/editOrderForm", method = RequestMethod.GET)
    public String editOrderForm(@RequestParam("orderNumber") String id, Map<String, Object> model) {
        Order order = dao.getOrderById(Integer.parseInt(id));

        String[] products = new String[mySupport.getProductList().size()];
        products = mySupport.getProductList().keySet().toArray(products);
        HashMap<String, String> productsMap = new HashMap<>();

        for (int i = 0; i < products.length; i++) {
            productsMap.put(products[i], products[i]);
        }

        String[] states = new String[mySupport.getTaxList().size()];
        states = mySupport.getTaxList().keySet().toArray(states);
        HashMap<String, String> statesMap = new HashMap<>();

        for (int i = 0; i < states.length; i++) {
            statesMap.put(states[i], states[i]);
        }

        model.put("states", statesMap);
        model.put("productTypes", productsMap);
        model.put("order", order);

        return "editOrderFormView";
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    public String editOrder(@ModelAttribute("order") Order order,
            Map<String, Object> model) {

        myOrderFactory.calcOrder(order);
        dao.updateOrder(order);

        return "redirect:displayOrders";
    }
}
