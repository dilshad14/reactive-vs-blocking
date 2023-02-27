package com.demo.spring.rvsb.reactive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/reactive")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    RestTemplate restTemplate;

    volatile AtomicInteger counter = new AtomicInteger(0);

    @Value("${backend-svc.url}")
    String backendSvcUrl;

    @GetMapping("/employees")
    public Flux employees() {
       return Flux.fromIterable(restTemplate.getForEntity(backendSvcUrl, List.class).getBody());
    }

    @GetMapping("/hi")
    public String hi() {
        return " Hi from BFF..!!";
    }

}
