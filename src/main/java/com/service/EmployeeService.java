package com.service;

import com.dto.request.EmployeeDtoIdRequest;
import com.dto.request.EmployeeDtoRequest;
import com.dto.response.ResponseDto;
import com.exception.NotFoundException;
import com.entity.Employee;
import com.projection.EmployeeProjection;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Employee save(EmployeeDtoRequest dtoRequest) {
        if (!departmentRepository.existsById(dtoRequest.getDepartmentId()))
            throw new NotFoundException("There is no department with id " + dtoRequest.getDepartmentId());

        Employee employee = new Employee(dtoRequest.getFirstName(), dtoRequest.getLastName(),
                dtoRequest.getPosition(), dtoRequest.getSalary(), dtoRequest.getDepartmentId());

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(EmployeeDtoIdRequest dtoRequest) {
        if (!employeeRepository.existsById(dtoRequest.getDepartmentId())) {
            throw new NotFoundException("The employee with id " + dtoRequest.getId() + " not found");
        }
        Employee employee = new Employee(dtoRequest.getFirstName(), dtoRequest.getLastName(),
                dtoRequest.getPosition(), dtoRequest.getSalary(), dtoRequest.getDepartmentId());
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee findById(Long id) {
        Optional<Employee> value = employeeRepository.findById(id);
        if (value.isEmpty()) throw new NotFoundException("The employee with id " + id + " not found");
        return value.get();
    }

    @Transactional
    public ResponseDto delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NotFoundException("The employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
        return new ResponseDto("The employee has been successfully deleted");
    }

    @Transactional
    public EmployeeProjection findEmployeeDataById(Long id) {
        return employeeRepository.findProjectionById(id);
    }
}
