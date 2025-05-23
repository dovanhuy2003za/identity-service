package com.huydo.identity_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.huydo.identity_service.dto.request.UserRequest;
import com.huydo.identity_service.enity.User;
import com.huydo.identity_service.mapper.UserMapper;
import com.huydo.identity_service.respository.UserRespository;

@Service
public class UserService {
    @Autowired
    private UserRespository userRespository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserRequest request) {
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRespository.save(user);
    }
    
}
