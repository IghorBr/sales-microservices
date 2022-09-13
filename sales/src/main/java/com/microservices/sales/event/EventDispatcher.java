package com.microservices.sales.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    // The exchange to use to send anything related to Multiplication
    private String newBuyExchange;

    // The routing key to use to send this particular event
    private String newBuyRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${new.buy.exchange}") final String newBuyExchange,
                    @Value("${new.buy.key}") final String newBuyRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.newBuyExchange = newBuyExchange;
        this.newBuyRoutingKey = newBuyRoutingKey;
    }

    public void send(final NewBuyEvent newBuyEvent) {
        rabbitTemplate.convertAndSend(
                newBuyExchange,
                newBuyRoutingKey,
                newBuyEvent);
    }
}
