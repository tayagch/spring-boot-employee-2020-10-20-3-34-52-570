package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private final List<Company> companies = new ArrayList<>();

//check
    @GetMapping
    public List<Company> getAll() {
        return companies;
    }
//check
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company) {
        companies.add(company);
        return company;
    }
//check
    @GetMapping("/{employeeNumber}")
    public Company get(@PathVariable int employeeId) {
        return companies.stream().filter(company -> company.getEmployeeNumber() == employeeId).findFirst().orElse(null);
    }
//check
    @PutMapping("/{employeeNumber}")
    public Company update(@PathVariable Integer employeeId, @RequestBody Company companyUpdate) {
        companies.stream().filter(company -> company.getEmployeeNumber() == employeeId).findFirst().ifPresent(company ->
        {
            companies.remove(company);
            companies.add(companyUpdate);
        });
        return companyUpdate;
    }
//check
    @DeleteMapping("/{employeeNumber}")
    public void delete(@PathVariable Integer employeeNumber) {
        companies.stream().filter(company -> company.getEmployeeNumber() == employeeNumber).findFirst().ifPresent(companies::remove);
    }
//
    @GetMapping(params = {"page" , "pageSize"})
    public List<Company> getByPage(@RequestParam() int page,@RequestParam() int pageSize){
        return companies.stream().skip(pageSize*(page-1)).limit(pageSize).collect(Collectors.toList());
    }
}
