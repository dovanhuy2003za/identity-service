package com.huydo.identity_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huydo.identity_service.dto.request.UserRequest;
import com.huydo.identity_service.service.EmailService;
import com.huydo.identity_service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserRequest request) {

        userService.createUser(request);
        emailService.sendEmail("huydo9924@gmail.com", "Welcome to our service", "Thank you for registering!");
        return "User created successfully";
    }

}
