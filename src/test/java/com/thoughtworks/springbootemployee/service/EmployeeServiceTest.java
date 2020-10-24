package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepositoryLegacy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    //Givens
    EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
    Employee employeeRequest = new Employee(1, "junjun", 10, "male", 200);
    List<Employee> expectedEmployees2 = asList(employeeRequest);
    List<Employee> expectedEmployees = asList(new Employee(), new Employee());

    @Test
    void should_get_all_when_get_employees() {
        //GIVEN
        when(repository.findAll()).thenReturn(expectedEmployees);
        EmployeeService service = new EmployeeService(repository);
        //WHEN
        List<Employee> actual = service.getAll();
        //THEN
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void should_create_employee_when_create_given_employee_request() {
        //GIVEN
        when(repository.save(employeeRequest)).thenReturn(employeeRequest);
        EmployeeService employeeService = new EmployeeService(repository);
        //WHEN
        Employee actual = employeeService.create(employeeRequest);
        //THEN
        Assertions.assertEquals(1, actual.getId());
    }

    @Test
    void should_delete_employee_when_delete_given_employee_request() {
        //given
        EmployeeService employeeService = new EmployeeService(repository);
        // when
        employeeService.delete(1);
        //then
        verify(repository).deleteById(1);

    }

    @Test
    void should_get_updated_employeeId_when_update_given_employee_request() {
        //GIVEN
        when(repository.findById(1)).thenReturn(Optional.ofNullable(employeeRequest));
        //WHEN
        EmployeeService employeeService = new EmployeeService(repository);
        employeeService.getById(1);
        employeeService.update(1,employeeRequest);

        //THEN
        verify(repository).save(employeeRequest);
    }

    @Test
    void should_get_employee_with_correct_gender_when_search_given_employee_request() {
        //GIVEN
        when(repository.findByGender("male")).thenReturn(expectedEmployees);
        //WHEN
        EmployeeService employeeService = new EmployeeService(repository);
        List<Employee> actual = employeeService.getByGender("male");
        //THEN
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void should_get_correct_page_size_when_given_getPage_employee_request() {
        //GIVEN
        Page<Employee> employeePage = new PageImpl<Employee>(expectedEmployees);
        Pageable pageable = PageRequest.of(1,3);
        when(repository.findAll(pageable)).thenReturn(employeePage);
        EmployeeService service = new EmployeeService(repository);
        //WHEN
        List<Employee> actual = service.getByPage(1, 3);
        //THEN
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    void should_get_employee_with_correct_id_when_search_given_employee_request() {
        //GIVEN
        when(repository.findById(1)).thenReturn(Optional.ofNullable(employeeRequest));
        //WHEN
        EmployeeService employeeService = new EmployeeService(repository);
        Employee actual = employeeService.getById(1);
        //THEN
        Assertions.assertEquals(1, actual.getId());
    }


}