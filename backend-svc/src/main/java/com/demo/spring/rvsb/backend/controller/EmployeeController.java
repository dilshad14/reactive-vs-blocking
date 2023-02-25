package com.demo.spring.rvsb.backend.controller;


import com.demo.spring.rvsb.backend.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/backend")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    volatile AtomicInteger counter = new AtomicInteger(0);

    @Value("${app.response.delay.Milis}")
    Integer appResponseDelayMillis;

    @GetMapping("/employees")
    public List<Employee> getEmployeeList() {
        return populateEmpList();

    }

    private List<Employee> populateEmpList() {
        List<Employee> lst = new ArrayList<>();
        for (int i = 1; i <= 102; i++) {
            lst.add(new Employee(getEmpId(), UUID.randomUUID()
                    .toString()));
        }
        logger.info("{} - Counter={} - Response={}"
                , System.getenv("HOSTNAME")
                , counter.incrementAndGet(), Arrays.toString(lst.toArray()));

        try {
            Thread.sleep(appResponseDelayMillis);
        } catch (InterruptedException e) {
            logger.error("EmployeeController.populateEmpList() - InterruptedException while sleeping");
        }
        return lst;
    }

    public int  getEmpId() {
        synchronized(this) {
            SecureRandom r = new SecureRandom();
            return 10000 + r.nextInt(20000);
        }
    }

    @GetMapping("/hi")
    public String hi() {
        return " Hi from Backend..!!";
    }


}
