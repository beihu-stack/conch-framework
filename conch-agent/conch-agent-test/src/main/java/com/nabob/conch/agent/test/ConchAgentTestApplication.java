package com.nabob.conch.agent.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@SpringBootApplication
public class ConchAgentTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConchAgentTestApplication.class, args);
    }

}
