package com.example.swmanagement;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@EnableJpaAuditing
public class SwManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwManagementApplication.class, args);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory((entityManager));
    }

    @PersistenceContext
    private EntityManager entityManager;
}
