/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.controller;

import com.swcguild.contactlistmvc.dao.UserDao;
import com.swcguild.contactlistmvc.model.User;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class UserController {
    
    private UserDao dao;
    private PasswordEncoder encoder;
    
    @Inject
    public UserController(UserDao dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }
    
    @RequestMapping(value="/displayUserForm", method=RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        User newUser = new User();
        model.put("user", newUser);
        return "addUserForm";
    }
    
    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();
        newUser.setUsername(req.getParameter("username"));
        //newUser.setPassword(req.getParameter("password"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }
       
        dao.addUser(newUser);
        
        return "addUserForm";
    }
    
    
}
