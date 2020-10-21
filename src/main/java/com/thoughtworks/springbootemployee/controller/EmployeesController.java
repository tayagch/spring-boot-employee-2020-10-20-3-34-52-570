package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    
    @GetMapping(params = "gender")
    public List<Employee> getByGender(@RequestParam("gender") String gender){
        return  employees.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<Employee> getByPage(@RequestParam() int page,@RequestParam() int pageSize){
        return employees.stream().skip(pageSize*(page-1)).limit(pageSize).collect(Collectors.toList());
    }

}
