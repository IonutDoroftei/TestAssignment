package com.homework.testassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TestAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAssignmentApplication.class, args);
    }
}

