package com.microservices.user.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ValueAmqpConfig {

    @Value("${amqp.config.topic.exchange}")
    private String QUEUE;
    @Value("${amqp.config.queue}")
    private String TOPIC_EXCHANGE;
}
