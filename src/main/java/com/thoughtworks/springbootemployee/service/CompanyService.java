package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFound;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    public static final String COMPANY_NOT_FOUND = "Company Not Found";
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
        if (findByCompanyId(companyId)!=null){
            companyUpdate.getEmployees().forEach(employeeRepository::save);
            companyUpdate.setCompanyId(companyId);
            return companyRepository.save(companyUpdate);
        }
        throw new CompanyNotFound(COMPANY_NOT_FOUND);
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
        return companyRepository.findById(companyId)
                .orElseThrow(()->new CompanyNotFound(COMPANY_NOT_FOUND)).getEmployees();
    }
}
