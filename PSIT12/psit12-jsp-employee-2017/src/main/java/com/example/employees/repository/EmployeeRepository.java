package com.example.employees.repository;

import java.util.List;

import com.example.employees.domain.Employee;

public interface EmployeeRepository {

    public long addEmployee(Employee employee);

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(long id);

    public void init();

    public boolean removeEmployee(long id);

    public boolean updateEmployee(Employee employee);
}
