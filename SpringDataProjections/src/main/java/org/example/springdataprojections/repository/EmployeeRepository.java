package org.example.springdataprojections.repository;

import org.example.springdataprojections.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
