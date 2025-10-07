package com.controller;

import com.dto.request.EmployeeDtoIdRequest;
import com.dto.request.EmployeeDtoRequest;
import com.dto.response.ResponseDto;
import com.entity.Employee;
import com.projection.EmployeeProjection;
import com.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody EmployeeDtoRequest dtoRequest) {
        Employee receivedEmployee = employeeService.save(dtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(receivedEmployee);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody EmployeeDtoIdRequest dtoRequest) {
        Employee receivedEmployee = employeeService.update(dtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(receivedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> employeeById(@PathVariable("id") Long id) {
        Employee receivedEmployee = employeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(receivedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
        ResponseDto responseDto = employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/projection/{id}")
    public ResponseEntity<EmployeeProjection> employeeLastName(@PathVariable("id") Long id) {
        EmployeeProjection projection = employeeService.findEmployeeDataById(id);
        return ResponseEntity.status(HttpStatus.OK).body(projection);
    }


}
