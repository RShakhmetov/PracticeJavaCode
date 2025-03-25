package org.example.springdataprojections.projection;

import org.example.springdataprojections.module.Employee;

public class EmployeeProjectionImpl implements EmployeeProjection {

    private final String fullName;

    private final String position;

    private final String departmentName;

    public EmployeeProjectionImpl(Employee employee) {
        this.fullName = employee.getFirstName() + " " + employee.getLastName();
        this.position = employee.getPosition();
        this.departmentName = employee.getDepartment() != null ? employee.getDepartment().getName() : null;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public String getDepartmentName() {
        return departmentName;
    }
}
