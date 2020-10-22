package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.CompanyRepositoryLegacy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Company findByCompanyId(Integer companyId){
        return repository.findById(companyId).get();
    }

    public Company update(Integer companyId, Company companyUpdate){
        companyUpdate.setCompanyId(companyId);
        return repository.save(companyUpdate);
    }

    public void delete(Integer companyId){
        Optional<Company> company = repository.findById(companyId);
        company.ifPresent(repository::delete);
    }

    public List<Company> getByPage(int page, int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        return repository.findAll(pageable).toList();
    }

    public List<Employee> getEmployees(Integer companyId){
        return repository.findById(companyId).get().getEmployees();
    }
}
