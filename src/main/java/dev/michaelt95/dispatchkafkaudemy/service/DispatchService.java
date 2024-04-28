package dev.michaelt95.dispatchkafkaudemy.service;

import org.springframework.stereotype.Service;

import dev.michaelt95.dispatchkafkaudemy.message.OrderCreated;

@Service
public class DispatchService {

    public void process(OrderCreated payload){
        // no-op
    }
}
