package com.microservices.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationDto {
    private final Long userId;
    private final String username;
    private final String password;
}
