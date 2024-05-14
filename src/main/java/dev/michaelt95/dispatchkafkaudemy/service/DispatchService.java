package dev.michaelt95.dispatchkafkaudemy.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;
import dev.michaelt95.dispatchkafkaudemy.message.OrderDispatched;
import dev.michaelt95.dispatchkafkaudemy.message.TrackingDispatched;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DispatchService {

    private static final String ORDER_DISPATCHED_TOPIC = "order-dispatched";
    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";
    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(OrderCreated payload) throws Exception {
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(payload.getOrderId())
                .build();

        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();

        TrackingDispatched trackingDispatched = TrackingDispatched.builder()
                .orderId(payload.getOrderId())
                .status("COMPLETED")
                .build();

        kafkaProducer.send(DISPATCH_TRACKING_TOPIC, trackingDispatched).get();
    }

}
