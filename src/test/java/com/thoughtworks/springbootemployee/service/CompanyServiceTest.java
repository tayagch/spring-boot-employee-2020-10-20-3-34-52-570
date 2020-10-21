package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompanyServiceTest {

    @Test
    void should_get_all_when_get_companies(){
        //GIVEN
        CompanyRepository repository = Mockito.mock(CompanyRepository.class);
        List<Company> expectedCompanies = asList(new Company(),new Company());
        when(repository.findAll()).thenReturn(expectedCompanies);
        CompanyService service = new CompanyService(repository);
        //WHEN
        List<Company> actual = service.getAll();
        //THEN
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void should_create_company_when_create_given_company_request(){

        List<Employee> employees = asList(new Employee(), new Employee());
        Company companyRequest = new Company("Alibaba",200,employees);
        CompanyRepository repository = Mockito.mock(CompanyRepository.class);
        when(repository.save(companyRequest)).thenReturn(companyRequest);
        CompanyService companyService = new CompanyService(repository);

        Company actual = companyService.create(companyRequest);

        Assertions.assertEquals("Alibaba", actual.getCompanyName());
    }

}