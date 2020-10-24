package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

public class CompanyRequest {
    private String companyName;

    private List<Employee> employees;

    public CompanyRequest() {
    }

    public CompanyRequest(String companyNames, List<Employee> employees ) {
        this.companyName = companyNames;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
