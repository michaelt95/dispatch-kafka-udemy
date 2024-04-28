package dev.michaelt95.dispatchkafkaudemy.util;

import java.util.UUID;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestEventData {

    public static OrderCreated buildOrderCreated(UUID orderId, String item) {
        return OrderCreated.builder()
                .orderId(orderId)
                .item(item)
                .build();
    }
}
