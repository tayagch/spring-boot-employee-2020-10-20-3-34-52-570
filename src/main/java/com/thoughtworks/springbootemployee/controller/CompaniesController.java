package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private final List<Company> companies = new ArrayList<>();


    @GetMapping
    public List<Company> getAll() {
        return companies;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company) {
        companies.add(company);
        return company;
    }

    @GetMapping("/{companyId}")
    public Company get(@PathVariable int companyId) {
        return companies.stream().filter(company -> company.getCompanyID() == companyId).findFirst().orElse(null);
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable Integer companyId, @RequestBody Company companyUpdate) {
        companies.stream().filter(company -> company.getCompanyID() == companyId).findFirst().ifPresent(company ->
        {
            companies.remove(company);
            companies.add(companyUpdate);
        });
        return companyUpdate;
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable Integer companyId) {
        companies.stream().filter(company -> company.getCompanyID() == companyId).findFirst().ifPresent(companies::remove);
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<Company> getByPage(@RequestParam() int page,@RequestParam() int pageSize){
        return companies.stream().skip(pageSize*(page-1)).limit(pageSize).collect(Collectors.toList());
    }
}
