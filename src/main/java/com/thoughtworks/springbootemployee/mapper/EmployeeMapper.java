package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse response = new EmployeeResponse();
        BeanUtils.copyProperties(employee, response);
        return response;
    }


    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return  employee;
    }
}
