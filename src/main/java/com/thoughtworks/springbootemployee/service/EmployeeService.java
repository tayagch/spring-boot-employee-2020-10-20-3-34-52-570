package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public void delete(Integer employeeId) {
        Employee employee = repository.findById(employeeId).get();
         repository.delete(employee);
    }

    public Employee update(Integer employeeId, Employee employeeRequest) {
        employeeRequest.setId(employeeId);
        return repository.save(employeeRequest);
    }

    public List<Employee> getByGender(String male) {
        return repository.findByGender(male);
    }

    public List<Employee> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return repository.findAll(pageable).toList();
    }

    public Optional<Employee> getById(Integer id) {
        return repository.findById(id);
    }
}
