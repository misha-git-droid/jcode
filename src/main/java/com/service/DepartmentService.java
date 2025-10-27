package com.service;

import com.dto.request.DepartmentDtoIdRequest;
import com.dto.request.DepartmentDtoRequest;
import com.dto.response.ResponseDto;
import com.exception.DepartmentNotFoundException;
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
        // 1. если говорить про тестирование, то поле в dtoRequest должно проверяться на корректность, т.к.
        // title может состоять, например, из букв, но не из символов (_=+№"!). Тут нужна отработка навыков
        // с validation
        Department department = new Department(dtoRequest.getTitle());
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(DepartmentDtoIdRequest dtoRequest) {
        Long departmentId = dtoRequest.getId();

        if (!departmentRepository.existsById(dtoRequest.getId())) {
            throw new DepartmentNotFoundException(departmentId);
        }

        Department department = new Department(dtoRequest.getTitle());
        return departmentRepository.save(department);
    }

    @Transactional
    public Department findById(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);

        if (optional.isEmpty()) throw new DepartmentNotFoundException(id);

        return optional.get();
    }

    @Transactional
    public ResponseDto delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }

        departmentRepository.deleteById(id);
        return new ResponseDto("The department has been successfully deleted");
    }
}
