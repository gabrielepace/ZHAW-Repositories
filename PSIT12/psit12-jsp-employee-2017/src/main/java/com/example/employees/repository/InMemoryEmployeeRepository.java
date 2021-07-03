/* Copyright 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.employees.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import com.example.employees.domain.Employee;

public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final List<Employee> employeeList = new ArrayList<>();
    private static final AtomicLong counter = new AtomicLong(100);

    public InMemoryEmployeeRepository() {
        // do nothing
    }

    @Override
    public long addEmployee(Employee employee) {
    	if(employee.getId() > 0) {
    		throw new IllegalArgumentException("Id ist not 0");
    	}
    	employee.setId(counter.incrementAndGet());
        this.employeeList.add(employee);
        return employee.getId();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeList;
    }

    @Override
    public Employee getEmployeeById(long id) {
        final Optional<Employee> match = this.employeeList.stream().filter(e -> e.getId() == id).findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new IllegalArgumentException("The Employee id " + id + " not found");
        }
    }

    @Override
    public void init() {
        this.employeeList.clear();
        
        this.addEmployee(new Employee("John", "Smith", "john.smith", "12-12-1980", "Manager", "Sales", "john.smith@abc.com", "076 345 6789"));
        this.addEmployee(new Employee("Laura", "Adams", "laura.adams", "02-11-1979", "Manager", "IT", "laura.adams@abc.com", "076 345 6789"));
        this.addEmployee(new Employee("Peter", "Williams", "peter.williams", "22-10-1966", "Coordinator", "HR", "peter.williams@abc.com", "076 345 6789"));
        this.addEmployee(new Employee("Joana", "Sanders", "joana.sanders", "11-11-1976", "Manager", "Marketing", "joana.sanders@abc.com", "076 345 6789"));
        this.addEmployee(new Employee("John", "Drake", "john.drake", "18-08-1988", "Coordinator", "Finance", "john.drake@abc.com", "076 345 6789"));
        this.addEmployee(new Employee("Samuel", "Williams", "samuel.williams", "22-03-1985", "Coordinator", "Finance", "samuel.williams@abc.com", "076 345 6789"));
    }

    @Override
    public boolean removeEmployee(long id) {
        final Predicate<Employee> employee = e -> e.getId() == id;
        return this.employeeList.removeIf(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        int matchIdx = 0;
        final Optional<Employee> match = this.employeeList.stream().filter(c -> c.getId() == employee.getId())
                .findFirst();
        if (match.isPresent()) {
            matchIdx = this.employeeList.indexOf(match.get());
            this.employeeList.set(matchIdx, employee);
            return true;
        } else {
            return false;
        }
    }
}
