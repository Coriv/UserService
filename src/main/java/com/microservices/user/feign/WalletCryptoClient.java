package com.microservices.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "WALLET-CRYPTO")
public interface WalletCryptoClient {

    void createCryptoWallets(@RequestParam("userId") Long userId);
}
