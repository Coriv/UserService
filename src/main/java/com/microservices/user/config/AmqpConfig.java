package com.microservices.user.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@RequiredArgsConstructor
public class AmqpConfig {

    private final ValueAmqpConfig amqp;

    @Bean
    public Queue queue() {
        return new Queue(amqp.getQUEUE());
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(amqp.getTOPIC_EXCHANGE());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
