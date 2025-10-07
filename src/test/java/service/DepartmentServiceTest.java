package service;

import com.dto.request.DepartmentDtoRequest;
import com.entity.Department;
import com.repository.DepartmentRepository;
import com.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    DepartmentRepository departmentRepository;
    @InjectMocks
    DepartmentService departmentService;

    @Test
    void save_shouldReturnSavedDepartment() {
        DepartmentDtoRequest dtoRequest = new DepartmentDtoRequest("title");
        Department expectedDepartment = new Department("title");
        when(departmentRepository.save(any(Department.class))).thenReturn(expectedDepartment);

        Department receivedDepartment = departmentService.save(dtoRequest);

        assertEquals(expectedDepartment, receivedDepartment);
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void save() {
        DepartmentDtoRequest departmentDtoRequest = new DepartmentDtoRequest("tett");
        Department department = new Department("tett");

        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        department.getId();
        Department expectedDepartment = departmentService.save(departmentDtoRequest);
        assertNotNull(expectedDepartment.getId());
    }
}
