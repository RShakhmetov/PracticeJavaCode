package org.example.springdataprojections.service;

import lombok.RequiredArgsConstructor;
import org.example.springdataprojections.module.Employee;
import org.example.springdataprojections.projection.EmployeeProjection;
import org.example.springdataprojections.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeProjection> getAllEmployees() {
        return employeeRepository.findAllProjectedBy();
    }

    public Employee getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id).orElse(null);
        if (employee1 == null) {
            throw new RuntimeException("Employee not found");
        }
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setSalary(employee.getSalary());
        employee1.setPosition(employee.getPosition());
        employee1.setDepartment(employee.getDepartment());
        return employeeRepository.save(employee1);
    }

    public Employee deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.delete(employee);
        return employee;
    }
}
