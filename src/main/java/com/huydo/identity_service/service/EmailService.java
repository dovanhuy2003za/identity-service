package com.huydo.identity_service.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    private static final int OTP_LENGTH = 6;
    
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dovanhuy2003za@gmail.com"); // Set your email address here
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public String generateOtp() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10)); // Tạo số ngẫu nhiên từ 0-9
        }
        return otp.toString();
    }
    public String sendOtp(String to) {
        String otp = generateOtp();
        String subject = "Your OTP Code";
        String body = "Your OTP code is: " + otp;
        sendEmail(to, subject, body);
        return otp;
    }



}
