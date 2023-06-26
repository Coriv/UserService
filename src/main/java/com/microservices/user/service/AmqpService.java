package com.microservices.user.service;

import com.microservices.user.config.ValueAmqpConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmqpService {

    private final RabbitTemplate rabbitTemplate;
    private final ValueAmqpConfig amqpConfig;

    public void notifyNewUserByEmail(String email) {
        rabbitTemplate.convertAndSend(amqpConfig.getTOPIC_EXCHANGE(), amqpConfig.getQUEUE(), email);
    }
}
