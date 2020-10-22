//package com.thoughtworks.springbootemployee.service;
//
//import com.thoughtworks.springbootemployee.model.Company;
//import com.thoughtworks.springbootemployee.model.Employee;
//import com.thoughtworks.springbootemployee.repository.CompanyRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//
//import static java.util.Arrays.asList;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class CompanyServiceTest {
//
//    //Givens
//    //should use @Before
//    CompanyRepository repository = Mockito.mock(CompanyRepository.class);
//    List<Company> expectedCompanies = asList(new Company(),new Company());
//    List<Employee> employees = asList(new Employee(), new Employee());
//    Company companyRequest = new Company("Alibaba",200,employees);
//
//    @Test
//    void should_get_all_when_get_companies(){
//        //GIVEN
//        when(repository.findAll()).thenReturn(expectedCompanies);
//        CompanyService service = new CompanyService(repository);
//        //WHEN
//        List<Company> actual = service.getAll();
//        //THEN
//        Assertions.assertEquals(2, actual.size());
//    }
//
//    @Test
//    void should_create_company_when_create_given_company_request(){
//        //GIVEN
//        when(repository.save(companyRequest)).thenReturn(companyRequest);
//        CompanyService companyService = new CompanyService(repository);
//        //WHEN
//        Company actual = companyService.create(companyRequest);
//        //THEN
//        Assertions.assertEquals("Alibaba", actual.getCompanyName());
//    }
//
//    @Test
//    void should_get_company_when_search_given_company_request(){
//        //GIVEN
//        when(repository.search(200)).thenReturn(companyRequest);
//        CompanyService companyService = new CompanyService(repository);
//        //WHEN
//        Company actual = companyService.findByCompanyId(200);
//        //THEN
//        Assertions.assertEquals("Alibaba", actual.getCompanyName());
//    }
//
//    @Test
//    void should_get_updated_company_when_update_given_company_request(){
//        //GIVEN
//        when(repository.update(200,companyRequest)).thenReturn(companyRequest);
//        CompanyService companyService = new CompanyService(repository);
//        //WHEN
//        Company actual = companyService.update(200,companyRequest);
//        //THEN
//        Assertions.assertEquals("Alibaba", actual.getCompanyName());
//    }
//
//    @Test
//    void should_get_delete_company_when_delete_given_company_request() {
//        //given
//        CompanyService companyService = new CompanyService(repository);
//        // when
//        companyService.delete(200);
//        //then
////        verify(repository).delete(200);
//    }
//
//    @Test
//    void should_get_company_when_getByPage_given_company_request() {
//        //GIVEN
//        when(repository.getByPage(1,3)).thenReturn(expectedCompanies);
//        CompanyService companyService = new CompanyService(repository);
//        //WHEN
//        List<Company> companyActual = companyService.getByPage(1,3);
//        //THEN
//        Assertions.assertEquals(2, companyActual.size());
//    }
//
//    @Test
//    void should_get_company_emplyees_when_getEmployees_given_company_request() {
//        //GIVEN
//        when(repository.getEmployees(200)).thenReturn(companyRequest.getEmployees());
//        CompanyService companyService = new CompanyService(repository);
//        //WHEN
//        List<Employee> actual = companyService.getEmployees(200);
//        //THEN
//        Assertions.assertEquals(companyRequest.getEmployees(), actual);
//    }
//
//}