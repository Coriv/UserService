package com.microservices.user.service;

import com.microservices.user.dto.AuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    @LoadBalanced
    private final RestTemplate restTemplate;
    public String registerUserAuth(AuthenticationDto.AuthenticationDtoBuilder authDto) {
        return restTemplate.postForObject("http://AUTHENTICATION/auth/register", authDto, String.class);
    }
}
