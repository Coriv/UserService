package com.microservices.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String idNumber;
    private final LocalDateTime dateOfJoin;
    private final String email;
    private final String username;
    private final String password;
}
