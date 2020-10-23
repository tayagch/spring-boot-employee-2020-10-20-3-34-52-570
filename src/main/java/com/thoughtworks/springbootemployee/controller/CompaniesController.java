package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.*;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private CompanyService companyService;
    private CompanyMapper companyMapper;
    private EmployeeMapper employeeMapper;

    public CompaniesController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> getAll() {
        List<Company> companies = companyService.getAll();
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse create(@RequestBody CompanyRequest company) {
        Company createdCompany = companyService.create(companyMapper.toEntity(company));
        return companyMapper.toResponse(createdCompany);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse get(@PathVariable Integer companyId) {

        return companyMapper.toResponse(companyService.findByCompanyId(companyId));
    }

    @PutMapping("/{companyId}")
    public CompanyResponse update(@PathVariable Integer companyId, @RequestBody CompanyRequest companyUpdate) {

        return  companyMapper.toResponse(companyService.update(companyId,companyMapper.toEntity(companyUpdate)));
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable Integer companyId) {
        companyService.delete(companyId);
    }

    @GetMapping(params = {"page" , "pageSize"})
    public List<CompanyResponse> getByPage(@RequestParam() int page,@RequestParam() int pageSize){
        //return companyService.getByPage(page,pageSize);
        List<Company> companies = companyService.getByPage(page,pageSize);
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{companyId}/employees")
    public List<EmployeeResponse> getEmployees(@PathVariable Integer companyId) {
        List<Employee> byEmployee = companyService.getEmployees(companyId);
        return byEmployee.stream().map(employeeMapper::toResponse).collect(Collectors.toList());

    }
}
