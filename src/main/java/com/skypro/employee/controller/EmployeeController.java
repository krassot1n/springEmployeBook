package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.reecord.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * HTTP методы
 * GET - получение ресурса или набора ресурсов
 * POST - создание ресурса
 * PUT - модицикация ресурса
 * PATCH - частичная модификая ресурчов
 * DELETE - удаление ресурсов
 */
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/emloyees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();

    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.addEmployee(employeeRequest);
    }
    @DeleteMapping("/employees/remove")
    public void removeEmployee(@RequestParam ){
    }

}
