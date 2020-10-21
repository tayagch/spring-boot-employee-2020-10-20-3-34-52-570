package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        employeeService.create(employee);
        return employee;
    }

    @GetMapping("/{employeeId}")
    public Employee get(@PathVariable int employeeId) {
        return employeeService.getById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employUpdate) {
        return employeeService.update(employeeId, employUpdate);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
    }

    @GetMapping(params = "gender")
    public List<Employee> getByGender(@RequestParam("gender") String gender){
        return  employeeService.getByGender(gender);
    }
    @GetMapping(params = {"page" , "pageSize"})
    public List<Employee> getByPage(@RequestParam() int page,@RequestParam() int pageSize){
        return employeeService.getByPage(page,pageSize);
    }

}
