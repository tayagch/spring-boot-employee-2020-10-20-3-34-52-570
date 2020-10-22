package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company create(Company company){
        return companyRepository.save(company);
    }

    public Company findByCompanyId(Integer companyId){
        return companyRepository.findById(companyId).get();
    }

    public Company update(Integer companyId, Company companyUpdate){
        if (findByCompanyId(companyId)==null){
            companyUpdate.getEmployees().forEach(employeeRepository::save);
            companyUpdate.setCompanyId(companyId);
            return companyRepository.save(companyUpdate);
        }
        throw new RuntimeException();
    }

    public void delete(Integer companyId){
        Optional<Company> company = companyRepository.findById(companyId);
        company.ifPresent(companyRepository::delete);
    }

    public List<Company> getByPage(int page, int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        return companyRepository.findAll(pageable).toList();
    }

    public List<Employee> getEmployees(Integer companyId){

        return Optional.ofNullable(companyRepository.findById(companyId).get().getEmployees())
                .orElseThrow(RuntimeException::new);
    }
}
