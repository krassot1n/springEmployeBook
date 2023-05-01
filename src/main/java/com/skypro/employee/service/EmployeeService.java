package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.reecord.EmployeeRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();
    public void removeEmployee(EmployeeRequest employeeRequest){
        this.employees.values().remove(employeeRequest);
    }

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(@NotNull EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;

    }
}
