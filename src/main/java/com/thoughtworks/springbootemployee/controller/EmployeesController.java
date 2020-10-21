package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAll(){return employees;}

    @GetMapping
    public Employee get(@PathVariable int employeeID){
        return  employees.stream().filter(employee1 -> employee1.getId() == employeeID).findFirst().orElse(null);
    }


}
