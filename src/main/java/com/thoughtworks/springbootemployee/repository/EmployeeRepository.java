package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll() {
        return null;
    }

    public Employee save(Employee employee) {
        return null;
    }

    public void delete(int i) {

    }

    public Employee update(Employee employeeRequest) {
        return null;
    }

    public Employee search(String gender ) {
        return null;
    }

    public List<Employee> getByPage(int i, int i1) {
        return null;
    }
}
