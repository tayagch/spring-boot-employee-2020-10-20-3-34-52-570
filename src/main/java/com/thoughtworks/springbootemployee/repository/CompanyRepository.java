package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public List<Company> findAll() {
        return null;
    }

    public Company save(Company company) {
        companies.add(company);
        return company;
    }

    public Company search(int employeeNumber) {
        return companies.stream().filter(company -> company.getEmployeeNumber() == employeeNumber).findFirst().orElse(null);
    }

    public Company update(int employeeNumber, Company companyUpdate) {
        companies.stream().filter(company -> company.getEmployeeNumber() == employeeNumber).findFirst().ifPresent(company ->
        {
            companies.remove(company);
            companies.add(companyUpdate);
        });
        return companyUpdate;
    }

    public Company delete(int employeeNumber) {
        return null;
    }

    public List<Company> getByPage(int page, int pageSize) {
        return null;
    }
}
