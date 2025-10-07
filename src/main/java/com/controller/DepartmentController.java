package com.controller;

import com.dto.request.DepartmentDtoIdRequest;
import com.dto.request.DepartmentDtoRequest;
import com.dto.response.ResponseDto;
import com.entity.Department;
import com.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody DepartmentDtoRequest dtoRequest) {
        Department receivedDepartment = departmentService.save(dtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(receivedDepartment);
    }

    @PutMapping
    public ResponseEntity<Department> update(@RequestBody DepartmentDtoIdRequest dtoRequest) {
        Department receivedDepartment = departmentService.update(dtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(receivedDepartment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> departmentById(@PathVariable("id") Long id) {
        Department receivedDepartment = departmentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(receivedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
        ResponseDto responseDto = departmentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
