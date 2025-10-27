package com.service;

import com.dto.request.EmployeeDtoIdRequest;
import com.dto.request.EmployeeDtoRequest;
import com.dto.response.ResponseDto;
import com.entity.Employee;
import com.exception.DepartmentNotFoundException;
import com.exception.EmployeeNotFoundException;
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
        Long departmentId = dtoRequest.getDepartmentId();

        if (!departmentRepository.existsById(departmentId))
            throw new DepartmentNotFoundException(departmentId);

        Employee employee = new Employee(dtoRequest.getFirstName(), dtoRequest.getLastName(),
                dtoRequest.getPosition(), dtoRequest.getSalary(), dtoRequest.getDepartmentId());

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(EmployeeDtoIdRequest dtoRequest) {
        Long employeeId = dtoRequest.getId();
        Long departmentId = dtoRequest.getDepartmentId();

        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException(employeeId);
        }

        if (!employeeRepository.existsById(departmentId)) {
            throw new DepartmentNotFoundException(departmentId);
        }

        Employee employee = new Employee(dtoRequest.getFirstName(), dtoRequest.getLastName(),
                dtoRequest.getPosition(), dtoRequest.getSalary(), dtoRequest.getDepartmentId());

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee findById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);

        if (optional.isEmpty()) throw new EmployeeNotFoundException(id);

        return optional.get();
    }

    @Transactional
    public ResponseDto delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }

        employeeRepository.deleteById(id);
        return new ResponseDto("The employee has been successfully deleted");
    }

    @Transactional
    public EmployeeProjection findEmployeeDataById(Long id) {
        return employeeRepository.findProjectionById(id);
    }
}
