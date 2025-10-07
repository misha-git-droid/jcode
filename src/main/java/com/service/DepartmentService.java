package com.service;

import com.dto.request.DepartmentDtoIdRequest;
import com.dto.request.DepartmentDtoRequest;
import com.dto.response.ResponseDto;
import com.exception.NotFoundException;
import com.entity.Department;
import com.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Department save(DepartmentDtoRequest dtoRequest) {
        Department department = new Department(dtoRequest.getTitle());
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(DepartmentDtoIdRequest dtoRequest) {
        if (!departmentRepository.existsById(dtoRequest.getId())) {
            throw new NotFoundException("The department with id " + dtoRequest.getId() + " not found");
        }
        Department department = new Department(dtoRequest.getTitle());
        return departmentRepository.save(department);
    }

    @Transactional
    public Department findById(Long id) {
        Optional<Department> value = departmentRepository.findById(id);
        if (value.isEmpty()) throw new NotFoundException("The department with id " + id + " not found");
        return value.get();
    }

    @Transactional
    public ResponseDto delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NotFoundException("The department with id " + id + " not found");
        }
        departmentRepository.deleteById(id);
        return new ResponseDto("The department has been successfully deleted");
    }
}
