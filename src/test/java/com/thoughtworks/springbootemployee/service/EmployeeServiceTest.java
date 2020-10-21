package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);

    @Test
    void should_get_all_when_get_employees(){
        //GIVEN
        List<Employee> expectedEmployees = asList(new Employee(),new Employee());
        when(repository.findAll()).thenReturn(expectedEmployees);
        EmployeeService service = new EmployeeService(repository);
        //WHEN
        List<Employee> actual = service.getAll();
        //THEN
        Assertions.assertEquals(2, actual.size());
    }
    @Test
    void should_create_employee_when_create_given_employee_request(){

        Employee employeeRequest = new Employee(1, "junjun", 10, "male", 200 );
        when(repository.save(employeeRequest)).thenReturn(employeeRequest);
        EmployeeService employeeService = new EmployeeService(repository);

        Employee actual = employeeService.create(employeeRequest);

        Assertions.assertEquals(1, actual.getId());
    }

    @Test
    void should_delete_employee_when_delete_given_employee_request(){
        //given
        EmployeeService employeeService = new EmployeeService(repository);
        // when
        employeeService.delete(1);
        //then
        verify(repository).delete(1);

    }
    @Test
    void should_get_updated_employeeId_when_update_given_employee_request(){
        //GIVEN
        Employee employeeRequest = new Employee(1, "junjun", 10, "male", 200 );
        when(repository.update(employeeRequest)).thenReturn(employeeRequest);

        EmployeeService employeeService = new EmployeeService(repository);
        Employee actual = employeeService.update(employeeRequest);

        Assertions.assertEquals(1, actual.getId());
    }
    @Test
    void should_get_employee_with_correct_gender_when_search_given_employee_request(){
        //GIVEN
        Employee employeeRequest = new Employee(1, "junjun", 10, "male", 200 );
        when(repository.search("male")).thenReturn(employeeRequest);

        EmployeeService employeeService = new EmployeeService(repository);
        Employee actual = employeeService.search("male");

        Assertions.assertEquals("male", actual.getGender());
    }
    @Test
    void should_get_correct_page_size_when_given_getPage_employee_request(){
        //GIVEN
        List<Employee> expectedEmployees = asList(new Employee(),new Employee());
        when(repository.getByPage(1, 5)).thenReturn(expectedEmployees);
        EmployeeService service = new EmployeeService(repository);
        //WHEN
        List<Employee> actual = service.getByPage(1,5);
        //THEN
        Assertions.assertEquals(2, actual.size());
    }

    

}