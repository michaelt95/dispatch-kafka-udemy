package dev.michaelt95.dispatchkafkaudemy.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;
import dev.michaelt95.dispatchkafkaudemy.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedHandler {

    private final DispatchService dispatchService;

    @KafkaListener(
            id = "orderConsumerClient",
            topics = "order.created.custom",
            groupId = "dispatch.order.created.consumer.custom",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderCreated payload){
        log.info("Received message payload: {}", payload);
        try {
            dispatchService.process(payload);
        } catch (Exception e) {
            log.error("Error processing payload: {}", payload, e);
        }
    }

}
