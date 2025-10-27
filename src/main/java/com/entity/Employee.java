package com.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private Integer salary;
    private Long departmentId;

    public Employee(String firstName, String lastName, String position,
                    Integer salary, Long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public Integer getSalary() {
        return salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}
