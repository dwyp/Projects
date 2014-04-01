/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.contactlistmvc.dao;

import com.swcguild.contactlistmvc.model.User;

/**
 *
 * @author apprentice
 */
public interface UserDao {
    
    public User addUser(User newUser);
    
    public void deleteUser(String username);
    
}
