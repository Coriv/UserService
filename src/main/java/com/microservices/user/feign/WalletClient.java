package com.microservices.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "WALLET")
public interface WalletClient {

    @PostMapping("/create")
    void createWalletForNewUser(@RequestParam("userId") Long userId);
}
