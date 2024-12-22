package com.omar.fin_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FinTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinTrackerApplication.class, args);
    }
}