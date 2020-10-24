package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CompanyServiceTest {

    //Givens
    //should use @Before
    CompanyRepository repository = Mockito.mock(CompanyRepository.class);
    EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
    List<Company> expectedCompanies = asList(new Company(),new Company());
    List<Employee> employees = asList(new Employee(), new Employee());
    Company companyRequest = new Company(1,"Alibaba",employees);

    @Test
    void should_get_all_when_get_companies(){
        //GIVEN
        when(repository.findAll()).thenReturn(expectedCompanies);
        CompanyService service = new CompanyService(repository,employeeRepository);
        //WHEN
        List<Company> actual = service.getAll();
        //THEN
        assertEquals(2, actual.size());
    }

    @Test
    void should_create_company_when_create_given_company_request(){
        //GIVEN
        when(repository.save(companyRequest)).thenReturn(companyRequest);
        CompanyService companyService = new CompanyService(repository,employeeRepository);
        //WHEN
        Company actual = companyService.create(companyRequest);
        //THEN
        assertEquals("Alibaba", actual.getCompanyName());
    }

    @Test
    void should_get_company_when_search_given_company_request(){
        //GIVEN
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(companyRequest));
        CompanyService companyService = new CompanyService(repository,employeeRepository);
        //WHEN
        Company actual = companyService.findByCompanyId(1);
        //THEN
        assertEquals("Alibaba", actual.getCompanyName());
    }

    @Test
    void should_get_updated_company_when_update_given_company_request(){
        //GIVEN
        when(repository.findById(1)).thenReturn(Optional.ofNullable(companyRequest));
        CompanyService companyService = new CompanyService(repository,employeeRepository);
        companyService.findByCompanyId(1);
        //WHEN

        companyService.update(1,companyRequest);
        //THEN
        verify(repository).save(companyRequest);
    }

    @Test
    void should_get_delete_company_when_delete_given_company_request() {
        //given
        when(repository.save(companyRequest)).thenReturn(companyRequest);
        CompanyService companyService = new CompanyService(repository,employeeRepository);
        // when
        companyService.delete(1);
        //then
        assertEquals(0,companyService.getAll().size());
    }

    @Test
    void should_get_company_when_getByPage_given_company_request() {
        //GIVEN
        Page<Company> companyPage = new PageImpl<Company>(expectedCompanies);
        Pageable pageable = PageRequest.of(1,3);
        when(repository.findAll(pageable)).thenReturn(companyPage);
        CompanyService companyService = new CompanyService(repository,employeeRepository);
        //WHEN
        List<Company> companyActual = companyService.getByPage(1,3);
        //THEN
        assertEquals(2, companyActual.size());
    }

    @Test
    void should_get_company_emplyees_when_getEmployees_given_company_request() {
        //GIVEN
        when(repository.findById(1)).thenReturn(Optional.ofNullable(companyRequest));
        CompanyService companyService = new CompanyService(repository,employeeRepository);
        //WHEN
        List<Employee> actual = companyService.getEmployees(1);
        //THEN
        assertEquals(companyRequest.getEmployees(), actual);
    }

}