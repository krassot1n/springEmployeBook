package com.skypro.employee.service;

import com.skypro.employee.exceptions.EmployeeAlreadyAddedException;
import com.skypro.employee.exceptions.EmployeeNotFoundException;
import com.skypro.employee.exceptions.EmployeeStorageIsFullException;
import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class EmployeeService {
    int maxSize = 3;
    private final List<Employee> employees = new ArrayList<>(maxSize);

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void add(Employee employee) {
        if (employees.size() > maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        for (Employee em : employees) {
            if (em.equals(employee)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        employees.add(employee);

    }

    public Employee remove(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(employee)) {
                employees.remove(i);
            } else {
                throw new EmployeeNotFoundException();
            }
        }
        return employee;
    }

    public Employee find(Employee employee) {
        for (Employee emp : employees) {
            if (emp.equals(employee)) {
                return emp;
            }
        }
        throw new EmployeeNotFoundException();
    }
}
