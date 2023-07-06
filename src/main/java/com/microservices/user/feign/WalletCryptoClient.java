package com.microservices.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "WALLET-CRYPTO")
public interface WalletCryptoClient {

    @PostMapping(value = "v1/wallet-crypto/createCryptoWallets")
    void createCryptoWallets(@RequestParam("userId") Long userId);
}
