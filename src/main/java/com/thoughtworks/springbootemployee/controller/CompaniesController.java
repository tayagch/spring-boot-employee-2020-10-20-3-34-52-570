package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company) {
        return companyService.create(company);
    }

    @GetMapping("/{employeeNumber}")
    public Company get(@PathVariable int employeeId) {
        return companyService.search(employeeId);
    }

    @PutMapping("/{employeeNumber}")
    public Company update(@PathVariable Integer employeeId, @RequestBody Company companyUpdate) {
        return companyService.update(employeeId, companyUpdate);
    }

    @DeleteMapping("/{employeeNumber}")
    public void delete(@PathVariable Integer employeeNumber) {
        companyService.delete(employeeNumber);
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<Company> getByPage(@RequestParam() int page,@RequestParam() int pageSize){
        return companyService.getByPage(page,pageSize);
    }
}
