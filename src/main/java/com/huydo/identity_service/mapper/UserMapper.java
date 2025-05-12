package com.huydo.identity_service.mapper;

import org.mapstruct.Mapper;


import com.huydo.identity_service.dto.request.UserRequest;
import com.huydo.identity_service.dto.response.UserResponse;
import com.huydo.identity_service.enity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);
}
