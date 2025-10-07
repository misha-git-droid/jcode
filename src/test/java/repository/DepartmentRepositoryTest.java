package repository;

import com.entity.Department;
import com.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DepartmentRepositoryTest {

    @Mock
    DepartmentRepository departmentRepository;

    @Test
    void save() {
        Department department = new Department("test");
        departmentRepository.save(department);
        assertNotNull(department.getId());
    }
}
