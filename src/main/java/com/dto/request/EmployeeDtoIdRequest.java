package com.dto.request;

import lombok.Getter;

@Getter
public class EmployeeDtoIdRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private Integer salary;
    private Long departmentId;
}
