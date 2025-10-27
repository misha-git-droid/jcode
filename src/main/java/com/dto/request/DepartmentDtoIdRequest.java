package com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentDtoIdRequest {
    private Long id;
    private String title;
}
