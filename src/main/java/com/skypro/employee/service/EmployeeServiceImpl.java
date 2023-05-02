package com.skypro.employee.service;

import com.skypro.employee.model.Employee;

public interface EmployeeServiceImpl {
    Employee addEmployee(String firstname, String lastName);
    Employee removeEmployee(String firstname, String lastName);

    Employee findEmployee(String firstname, String lastname);
}
