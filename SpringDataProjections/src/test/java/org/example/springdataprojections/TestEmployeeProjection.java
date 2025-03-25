package org.example.springdataprojections;

import org.example.springdataprojections.module.Department;
import org.example.springdataprojections.module.Employee;
import org.example.springdataprojections.projection.EmployeeProjectionImpl;
import org.example.springdataprojections.repository.EmployeeRepository;
import org.example.springdataprojections.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestEmployeeProjection {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void init() {

        Department department = new Department();
        department.setId(1L);
        department.setName("Department 1");

        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Smith");
        employee.setPosition("Middle");
        employee.setSalary(50000);
        employee.setDepartment(department);
    }

    @Test
    public void testEmployeeProjection() {
        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(employee));
        List<EmployeeProjectionImpl> employeeProjections = employeeService.getAllEmployees();

        assertEquals(1, employeeProjections.size());
        assertEquals("John Smith", employeeProjections.get(0).getFullName());
        assertEquals("Middle", employeeProjections.get(0).getPosition());
        assertEquals("Department 1", employeeProjections.get(0).getDepartmentName());
    }
}
