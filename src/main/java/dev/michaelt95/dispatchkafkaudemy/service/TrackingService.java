package dev.michaelt95.dispatchkafkaudemy.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;
import dev.michaelt95.dispatchkafkaudemy.message.TrackingDispatched;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrackingService {

    @KafkaListener(
            id = "dispatchTrackingClient",
            topics = "dispatch.tracking",
            groupId = "dispatch.tracking.created.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(TrackingDispatched payload){
        log.info("Received message payload: {}", payload);
    }

}
