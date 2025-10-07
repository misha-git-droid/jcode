package com.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    private String id;
    private String title;

    public Department(String title) {
        this.title = title;
    }
}
