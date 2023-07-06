package com.microservices.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AuthenticationDto {
    private final Long userId;
    private final String username;
    private final String password;
}
