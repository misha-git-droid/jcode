package com.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Setter
@Getter
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private Integer salary;
    private Long departmentId;

    public Employee(String firstName, String lastName, String position,
                    Integer salary, Long departmentId) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.departmentId = departmentId;
    }
}
