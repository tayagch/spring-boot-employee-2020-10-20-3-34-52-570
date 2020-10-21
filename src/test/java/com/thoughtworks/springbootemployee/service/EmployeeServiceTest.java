package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Test
    void should_get_all_when_get_employees(){
        //GIVEN
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
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
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        when(repository.save(employeeRequest)).thenReturn(employeeRequest);
        EmployeeService employeeService = new EmployeeService(repository);

        Employee actual = employeeService.create(employeeRequest);

        Assertions.assertEquals(1, actual.getId());
    }
    @Test
    void should_delete_employee_when_delete_given_employee_request(){
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        doNothing().when(repository).delete(1);

        EmployeeService service = new EmployeeService(repository);
        service.delete(1);

    }
    @Test
    void should_return_junjun_name_when_get_employee_request(){

        Employee employeeRequest = new Employee(1, "junjun", 10, "male", 200 );
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        when(repository.save(employeeRequest)).thenReturn(employeeRequest);
        EmployeeService employeeService = new EmployeeService(repository);
        Employee actual = employeeService.create(employeeRequest);

        Assertions.assertEquals("junjun", actual.getName());
    }

    

}