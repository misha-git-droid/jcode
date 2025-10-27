package com.dto.request;

public class EmployeeDtoIdRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private Integer salary;
    private Long departmentId;

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
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
