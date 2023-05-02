package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
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
@RequestMapping("/emloyees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam ("firstName") String firstName,
                              @RequestParam ("lastName") String lastName) {

        return employeeService.addEmployee(firstName,lastName);

    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {

        return employeeService.removeEmployee(firstName,lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam(value = "firstName") String firstName,
                         @RequestParam(value = "lastName") String lastName) {
        return employeeService.findEmployee(firstName,lastName);
    }

}
