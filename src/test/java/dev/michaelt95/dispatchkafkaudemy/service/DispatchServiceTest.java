package dev.michaelt95.dispatchkafkaudemy.service;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;
import dev.michaelt95.dispatchkafkaudemy.message.OrderDispatched;
import dev.michaelt95.dispatchkafkaudemy.util.TestEventData;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DispatchServiceTest {

    private DispatchService service;
    private KafkaTemplate kafkaProducerMock;

    @BeforeEach
    void setUp() {
        kafkaProducerMock = mock(KafkaTemplate.class);
        service = new DispatchService(kafkaProducerMock);
    }

    void process_Success() throws Exception{
        when(kafkaProducerMock.send(anyString(), any(OrderDispatched.class))).thenReturn(mock(CompletableFuture.class));

        OrderCreated orderCreated = TestEventData.buildOrderCreated(randomUUID(), randomUUID().toString());
        service.process(orderCreated);
        verify(kafkaProducerMock, times(1)).send(eq("order.dispatched"), any(OrderDispatched.class));
    }

    void process_ProducerThrowsException() {
        OrderCreated orderCreated = TestEventData.buildOrderCreated(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Producer failure"))
                .when(kafkaProducerMock)
                .send(eq("order.dispatched"), any(OrderDispatched.class));

        Exception exception = assertThrows(RuntimeException.class, () -> service.process(orderCreated));

        verify(kafkaProducerMock, times(1)).send(eq("order.dispatched"), any(OrderDispatched.class));
        assertThat(exception.getMessage(), equalTo("Producer failure"));
    }

}