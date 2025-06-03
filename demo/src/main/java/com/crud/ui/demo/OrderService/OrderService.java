package com.crud.ui.demo.OrderService;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crud.ui.demo.model.Order;

@Service
public class OrderService {

    private final RestTemplate restTemplate;


    public OrderService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    public List<Order> getAllOrders() {
        ResponseEntity<Order[]> response = restTemplate.getForEntity("http://localhost:3001/orders", Order[].class);
        return Arrays.asList(response.getBody());
    }
    
}
