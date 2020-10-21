package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAll() {
        return repository.findAll();
    }

    public Company create(Company company){
        return repository.save(company);
    }

    public Company search(int employeeNumber){
        return repository.search(employeeNumber);
    }

    public Company update(int employeeNumber, Company companyUpdate){
        return repository.update(employeeNumber,companyUpdate);
    }

    public void delete(int employeeNumber){
        repository.delete(employeeNumber);
    }

    public List<Company> getByPage(int page, int pageSize){
        return repository.getByPage(page ,pageSize);
    }
}
