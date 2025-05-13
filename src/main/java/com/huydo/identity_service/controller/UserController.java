package com.huydo.identity_service.controller;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.huydo.identity_service.dto.request.UserRequest;
import com.huydo.identity_service.dto.request.VerifyOtpRequest;
import com.huydo.identity_service.service.EmailService;
import com.huydo.identity_service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    private Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private Map<String, UserRequest> userStorage = new ConcurrentHashMap<>();

    @PostMapping("/create")
    public String createUser(@RequestBody UserRequest request) {
        String otp= emailService.sendOtp(request.getEmail());
        otpStorage.put(request.getEmail(), otp);
        userStorage.put(request.getEmail(), request);
        // userService.createUser(request);
        return "User created successfully";
    }
    @PostMapping("/verify_otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest request) {
        String storeOtp= otpStorage.get(request.getEmail());
        if (storeOtp !=null && storeOtp.equals(request.getOtp())) {
            otpStorage.remove(request.getEmail());
            UserRequest userRequest = userStorage.get(request.getEmail());
            if (userRequest != null) {
                userService.createUser(userRequest);
                userStorage.remove(request.getEmail()); 
                return "OTP verified and user registered successfully!";
            } else {
                return "User information not found. Please try again.";
            }
        } else {
            return "Invalid OTP";
            
        }
        
    }

}
