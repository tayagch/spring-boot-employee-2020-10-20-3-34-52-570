package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAll() {
        return companyService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company) {
        return companyService.create(company);
    }

    @GetMapping("/{companyId}")
    public Company get(@PathVariable Integer companyId) {
        return companyService.findByCompanyId(companyId);
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable Integer companyId, @RequestBody Company companyUpdate) {
        return companyService.update(companyId, companyUpdate);
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable Integer companyId) {
        companyService.delete(companyId);
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<Company> getByPage(@RequestParam() int page,@RequestParam() int pageSize){
        return companyService.getByPage(page,pageSize);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployees(@PathVariable Integer companyId) {
        return companyService.getEmployees(companyId);
    }
}
