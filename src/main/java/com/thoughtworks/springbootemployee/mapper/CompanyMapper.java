package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.CompanyRequest;
import com.thoughtworks.springbootemployee.model.CompanyResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CompanyMapper {
    public CompanyResponse toResponse(Company company){
        CompanyResponse response = new CompanyResponse();
//        response.setEmployeeNumber(1);
//        response.setCompanyId(company.getCompanyId());
//        response.setCompanyName(company.getCompanyName());
//        response.setEmployees(company.getEmployees());

        //BeanUtls
        BeanUtils.copyProperties(company, response);
        response.setEmployeeNumber(company.getEmployees().size());
//        response.setEmployees(company.getEmployees());
        return response;
    }


    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        //company.setCompanyName(companyRequest.getCompanyName());
        BeanUtils.copyProperties(companyRequest, company);
        return  company;
    }
}
