package com.thoughtworks.springbootemployee.service;

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

    public List<Employee> getAll() {
        return null;
    }
}
