package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyRequest;
import com.thoughtworks.springbootemployee.model.CompanyResponse;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    public CompanyResponse toResponse(Company company){
        CompanyResponse response = new CompanyResponse();
//        response.setEmployeeNumber(company.getEmployees().size());
//        response.setCompanyId(company.getCompanyId());
//        response.setCompanyName(company.getCompanyName());
//        response.setEmployees(company.getEmployees());

        //BeanUtls
        BeanUtils.copyProperties(company, response);
        response.setEmployeeNumber(company.getEmployees().size());
        return response;
    }


    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        //company.setCompanyName(companyRequest.setCompanyName());
        BeanUtils.copyProperties(companyRequest, company);
        return  company;
    }
}
