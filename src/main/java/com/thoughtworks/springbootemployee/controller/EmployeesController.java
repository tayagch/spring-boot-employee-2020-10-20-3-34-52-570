package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();


    @GetMapping
    public List<Employee> getAll() {
        return employees;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @GetMapping("/{employeeId}")
    public Employee get(@PathVariable int employeeID) {
        return employees.stream().filter(employee1 -> employee1.getId() == employeeID).findFirst().orElse(null);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employUpdate) {
        employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().ifPresent(employee ->
        {
            employees.remove(employee);
            employees.add(employUpdate);
        });

        return employUpdate;
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().ifPresent(employees::remove);
    }


}
