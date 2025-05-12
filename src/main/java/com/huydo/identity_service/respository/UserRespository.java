package com.huydo.identity_service.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huydo.identity_service.enity.User;

public interface UserRespository extends JpaRepository<User, String>{

}
