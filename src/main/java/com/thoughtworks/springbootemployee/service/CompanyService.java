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
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.companyMapper = companyMapper;
    }

    public List<CompanyResponse> getAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    public CompanyResponse create(Company company){
        return companyMapper.toResponse(companyRepository.save(company));
    }

    public CompanyResponse findByCompanyId(Integer companyId){
        return companyMapper.toResponse(companyRepository.findById(companyId).get());
    }

    public CompanyResponse update(Integer companyId, Company companyUpdate){
        if (findByCompanyId(companyId)!=null){
            companyUpdate.getEmployees().forEach(employeeRepository::save);
            companyUpdate.setCompanyId(companyId);
            //return companyRepository.save(companyUpdate);
            companyMapper.toResponse(companyRepository.save(companyUpdate));
        }
        throw new CompanyNotFound(COMPANY_NOT_FOUND);
    }

    public void delete(Integer companyId){
        Optional<Company> company = companyRepository.findById(companyId);
        company.ifPresent(companyRepository::delete);
    }

    public List<CompanyResponse> getByPage(int page, int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        List<Company> companies = companyRepository.findAll(pageable).toList();
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    public List<Employee> getEmployees(Integer companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(()->new CompanyNotFound(COMPANY_NOT_FOUND)).getEmployees();

    }
}
