package com.microservices.user.feign;

import com.microservices.user.dto.AuthenticationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "AUTHENTICATION")
public interface AuthClient {

    @PostMapping(value = "v1/auth/register")
    String registerUserAuth(AuthenticationDto authDto);
}
