package com.microservices.delivery.event;

import com.microservices.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventHandler {

    private final DeliveryService deliveryService;

    private final Logger LOGGER = LoggerFactory.getLogger(EventHandler.class);

    @RabbitListener(queues = "${new.buy.queue}")
    void handleMultiplicationSolved(final NewBuyEvent event) {
        LOGGER.info("Pedido do usuario recebido", event.getUsername());
        try {
            deliveryService.newDelivery(event.getUsername(), event.getProducts());
        } catch (final Exception e) {
            LOGGER.error("Error when trying to process NewBuyEvent", e);
            // Avoids the event to be re-queued and reprocessed.
        }
    }
}
