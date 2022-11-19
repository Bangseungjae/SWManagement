package com.example.swmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SwManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwManagementApplication.class, args);
    }

}
