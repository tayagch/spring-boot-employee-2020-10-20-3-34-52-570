package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll() {
        return employees;
    }

    public Employee save(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public void delete(int employeeId) {
        employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().ifPresent(employees::remove);
    }

    public Employee update(int employeeId, Employee employeeRequest) {
        employees.stream().filter(employee -> employee.getId()==employeeId).findFirst().ifPresent(employee ->
        {employees.remove(employee); employees.add(employeeRequest);});
        return employeeRequest;
    }

    public List<Employee> getByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getByPage(int page, int pageSize) {
        return employees.stream().skip(pageSize*(page-1)).limit(pageSize).collect(Collectors.toList());

    }

    public Employee getById(int id) {
        return employees.stream().filter(employee1 -> employee1.getId() == id).findFirst().orElse(null);
    }
}
