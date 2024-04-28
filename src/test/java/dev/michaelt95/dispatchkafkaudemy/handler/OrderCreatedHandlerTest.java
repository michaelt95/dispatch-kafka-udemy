package dev.michaelt95.dispatchkafkaudemy.handler;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;
import dev.michaelt95.dispatchkafkaudemy.service.DispatchService;
import dev.michaelt95.dispatchkafkaudemy.util.TestEventData;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler handler;
    private DispatchService dispatchServiceMock;

    @BeforeEach
    void setUp() {
        dispatchServiceMock= mock(DispatchService.class);
        handler = new OrderCreatedHandler(dispatchServiceMock);
    }

    @Test
    void listen() {
        final OrderCreated orderCreated = TestEventData.buildOrderCreated(randomUUID(), randomUUID().toString());
        handler.listen(orderCreated);

        verify(dispatchServiceMock, times(1)).process(orderCreated);
    }
}