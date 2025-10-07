package com;

import com.entity.Department;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.UUID;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public BeforeConvertCallback<Department> beforeConvertCallback() {
        return (department) -> {
            if (department.getId() == null) {
                department.setId(UUID.randomUUID().toString());
            }
            return department;
        };
    }
}