package com.skypro.employee.service;

import com.skypro.employee.exceptions.EmployeeAlreadyAddedException;
import com.skypro.employee.exceptions.EmployeeNotFoundException;
import com.skypro.employee.exceptions.EmployeeStorageIsFullException;
import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class EmployeeService implements EmployeeServiceImpl {
    int maxSize = 3;
    private final List<Employee> employees = new ArrayList<>(maxSize);

    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees);
    }

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.contains(employee))
            throw new EmployeeAlreadyAddedException("В коллекции уже есть такой сотрудник ");

        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstname, String lastName) {
        Employee employee = new Employee(firstname, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Удаление не возможно-сотрудник не найден");
    }

    @Override
    public Employee findEmployee(String firstname, String lastName) {
        Employee employee = new Employee(firstname, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }
}
