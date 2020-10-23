package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.List;

public class CompanyResponse {
    private Integer companyId;
    private String companyName;
    private Integer employeeNumber;

    private List<Employee> employees;

    public CompanyResponse() {
    }

    public CompanyResponse(Integer companyId,String companyName, int employeeNumber, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeeNumber = employeeNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
