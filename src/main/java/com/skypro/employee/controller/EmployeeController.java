package com.skypro.employee.controller;

import com.skypro.employee.exceptions.EmployeeAlreadyAddedException;
import com.skypro.employee.exceptions.EmployeeNotFoundException;
import com.skypro.employee.exceptions.EmployeeStorageIsFullException;
import com.skypro.employee.model.Employee;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;



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

    @GetMapping("/getAll")
    public String getAllEmployees() {
        return this.employeeService.getAllEmployees().toString();
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam ("firstName") String firstName,
                              @RequestParam ("lastName") String lastName) {
        Employee employee = new Employee(firstName,lastName);
        try {
            employeeService.add(employee);
        } catch (EmployeeStorageIsFullException e) {
            throw new RuntimeException("Коллекция сотрудников переполнена");
        } catch (EmployeeAlreadyAddedException e) {
            throw new RuntimeException("В коллекции уже есть такой сотрудник");
        }
        return "сотрудник " + firstName + " " + lastName + " добавлен";

    }
    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.remove(employee);
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException("Удаление не выполнено, такой сотрудник не существует");
        }
        return "сотрудник " + firstName + " " + lastName + " удален";
    }
    @GetMapping("/find")
    public String findEmployee(@RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName
    ) {
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeService.find(employee);
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(" Сотрудник с таким именем не найден.");

        }
        return "Сотрудник найден: " + employeeService.find(employee);
    }

}
