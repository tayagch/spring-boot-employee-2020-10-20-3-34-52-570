package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound() {

    }

    public EmployeeNotFound(String employeeNotFound) {
       super(employeeNotFound);
    }
}
