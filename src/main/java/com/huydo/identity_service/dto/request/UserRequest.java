package com.huydo.identity_service.dto.request;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserRequest {
    String username;
    String password;
    String email;
}
