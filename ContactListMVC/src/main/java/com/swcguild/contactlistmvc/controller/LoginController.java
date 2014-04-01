package com.swcguild.contactlistmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// #1 - all requests of the form /spring/login come here
@RequestMapping("/login")
public class LoginController {
    // #2 - respond to all GET requests for /spring/login
    @RequestMapping(method = RequestMethod.GET)
    public void showLoginForm() {
    }
}
