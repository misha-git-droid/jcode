package service;

import com.dto.request.DepartmentDtoIdRequest;
import com.dto.request.DepartmentDtoRequest;
import com.dto.response.ResponseDto;
import com.entity.Department;
import com.exception.DepartmentNotFoundException;
import com.repository.DepartmentRepository;
import com.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    DepartmentRepository departmentRepository;
    @InjectMocks
    DepartmentService departmentService;

    @Test
    void save_whenTitleValid_shouldReturnSavedDepartment() {
        DepartmentDtoRequest dtoRequest = new DepartmentDtoRequest("title");
        Department expectedDepartment = new Department("title");
        when(departmentRepository.save(any(Department.class))).thenReturn(expectedDepartment);

        Department receivedDepartment = departmentService.save(dtoRequest);

        assertEquals(expectedDepartment, receivedDepartment);
        verify(departmentRepository, times(1)).save(any(Department.class));
    }


    @Test
    void update_whenDepartmentExists_shouldReturnUpdatedDepartment() {
        Long departmentId = 1L;
        DepartmentDtoIdRequest dtoIdRequest = new DepartmentDtoIdRequest(departmentId, "updatedTest");
        Department expectedDepartment = new Department("updatedTest");
        expectedDepartment.setId(departmentId);

        when(departmentRepository.existsById(departmentId)).thenReturn(true);
        when(departmentRepository.save(any(Department.class))).thenReturn(expectedDepartment);

        Department receivedDepartment = departmentService.update(dtoIdRequest);

        assertEquals(receivedDepartment, expectedDepartment);
    }

    @Test
    void update_whenDepartmentNotExists_shouldThrowDepartmentNotFoundException() {
        Long departmentId = 555L;
        DepartmentDtoIdRequest dtoRequest = new DepartmentDtoIdRequest(departmentId, "test");
        when(departmentRepository.existsById(dtoRequest.getId())).thenReturn(false);

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.update(dtoRequest));
    }

    @Test
    void departmentById_whenDepartmentExists_shouldReturnDepartment() {
        Long departmentId = 1L;
        Department expectedDepartment = new Department("test");
        expectedDepartment.setId(departmentId);
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(expectedDepartment));

        Department receivedDepartment = departmentService.findById(departmentId);

        assertEquals(receivedDepartment, expectedDepartment);
    }

    @Test
    void departmentById_whenDepartmentNotExists_shouldThrowDepartmentNotFoundException() {
        Long departmentId = 555L;
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.findById(departmentId));
    }

    @Test
    void delete_whenDepartmentExists_shouldReturnResponseDto() {
        Long departmentId = 1L;
        ResponseDto expectedResponse = new ResponseDto("The department has been successfully deleted");
        when(departmentRepository.existsById(departmentId)).thenReturn(true);

        ResponseDto receivedResponse = departmentService.delete(departmentId);

        assertEquals(receivedResponse.getMessage(), expectedResponse.getMessage());
    }

    @Test
    void delete_whenDepartmentNotExists_shouldThrowDepartmentNotFoundException() {
        Long departmentId = 555L;
        when(departmentRepository.existsById(departmentId)).thenReturn(false);

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.delete(departmentId));
    }
}
