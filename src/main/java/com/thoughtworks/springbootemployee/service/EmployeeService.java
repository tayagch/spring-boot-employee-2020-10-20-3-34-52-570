package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFound;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    public static final String EMPLOYEE_NOT_FOUND = "Employee Not Found";
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
        repository.deleteById(employeeId);
    }

    public Employee update(Integer employeeId, Employee employeeRequest) {
        if(repository.findById(employeeId).isPresent()){
            employeeRequest.setId(employeeId);
            return repository.save(employeeRequest);
        }
        throw new EmployeeNotFound(EMPLOYEE_NOT_FOUND);
    }

    public List<Employee> getByGender(String gender) {
        return repository.findByGender(gender);
    }

    public List<Employee> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return repository.findAll(pageable).toList();
    }

    public Employee getById(Integer employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFound(EMPLOYEE_NOT_FOUND));
    }
}
