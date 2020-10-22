package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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

    public void delete(int employeeId) {
         repository.delete(employeeId);
    }

    public Employee update(Integer employeeId, Employee employee) {
        Optional<Employee> employee1 = getById(employeeId);
        return repository.save(employee1);
    }

    public List<Employee> getByGender(String male) {
        return repository.findByGender(male);
    }

    public List<Employee> getByPage(int page, int pageSize) {
        return repository.findByPage(page,pageSize);
    }

    public Optional<Employee> getById(Integer id) {
        return repository.findById(id);
    }
}
