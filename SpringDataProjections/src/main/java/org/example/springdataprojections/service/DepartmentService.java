package org.example.springdataprojections.service;

import lombok.RequiredArgsConstructor;
import org.example.springdataprojections.module.Department;
import org.example.springdataprojections.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        return department;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        Department oldDepartment = departmentRepository.findById(id).orElse(null);
        if (oldDepartment == null) {
            throw new RuntimeException("Department not found");
        }
        oldDepartment.setName(department.getName());
        return departmentRepository.save(oldDepartment);
    }

    public Department deleteDepartment(Long id) {
        Department oldDepartment = departmentRepository.findById(id).orElse(null);
        if (oldDepartment == null) {
            throw new RuntimeException("Department not found");
        }
        departmentRepository.delete(oldDepartment);
        return oldDepartment;
    }
}
