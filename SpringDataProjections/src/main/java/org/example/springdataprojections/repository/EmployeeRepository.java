package org.example.springdataprojections.repository;

import org.example.springdataprojections.module.Employee;
import org.example.springdataprojections.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e.position AS position, d.name AS departmentName, CONCAT(e.firstName, ' ', e.lastName) AS fullName " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeProjection> findAllProjectedBy();
}
