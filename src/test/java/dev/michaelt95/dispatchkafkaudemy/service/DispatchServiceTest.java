package dev.michaelt95.dispatchkafkaudemy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;
import dev.michaelt95.dispatchkafkaudemy.util.TestEventData;

import static java.util.UUID.randomUUID;

class DispatchServiceTest {

    private DispatchService service;

    @BeforeEach
    void setUp() {
        service = new DispatchService();
    }

    @Test
    void process() {
        final OrderCreated orderCreated = TestEventData.buildOrderCreated(randomUUID(), randomUUID().toString());
        service.process(orderCreated);
    }
}