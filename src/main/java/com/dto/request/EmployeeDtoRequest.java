package com.dto.request;

public class EmployeeDtoRequest {
    private String firstName;
    private String lastName;
    private String position;
    private Integer salary;
    private Long departmentId;

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
