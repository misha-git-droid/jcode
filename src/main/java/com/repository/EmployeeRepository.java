package com.repository;

import com.entity.Employee;
import com.projection.EmployeeProjection;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT e.first_name, e.position, d.title FROM employee e JOIN department d ON e.department_id = d.id WHERE e.id = :id")
    EmployeeProjection findProjectionById(Long id);
}
