package com.example.employees.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.employees.domain.Employee;

public class TestEmployeeController {

    @Test
    public void testAddEmployee() {
        EmployeeController controller = new EmployeeController();
        controller.doAction("add", new Employee(), null);
        List<Employee> list = controller.getAllEmployees();
        assertTrue(7 == list.size());
    }
    
    @Test
    public void testFailAddEmployee() {
    	assertThrows(IllegalArgumentException.class, () -> {
	        EmployeeController controller = new EmployeeController();
	        Employee employee = new Employee();
	        employee.setId(1024);
	        controller.doAction("add", employee, null);
    	});
    }
    
    @Test
    public void testFailGetEmployee() {
    	assertThrows(IllegalArgumentException.class, () -> {
	        EmployeeController controller = new EmployeeController();
	        controller.getEmployeeById(1024);
    	});
    }

    @Test
    public void testDefaultValues() {
        EmployeeController controller = new EmployeeController();
        List<Employee> list = controller.getAllEmployees();
        assertTrue(6 == list.size());
    }

    @Test
    public void testRemoveEmployee() {
        EmployeeController controller = new EmployeeController();
        List<Employee> listBefore = controller.getAllEmployees();
        long id = listBefore.get(0).getId();
        Employee employee = new Employee();
        employee.setId(id);
        controller.doAction("remove", employee, null);
        List<Employee> listAfter = controller.getAllEmployees();
        assertTrue(5 == listAfter.size());
    }
    
    @Test
    public void testUpdateEmployee() {
        EmployeeController controller = new EmployeeController();
        List<Employee> listBefore = controller.getAllEmployees();
        long id = listBefore.get(0).getId();
        Employee employee = controller.getEmployeeById(id);
        employee.setName("updateValueName");
        employee.setLastName("updateValueLastName");
        employee.setDepartment("updateValueDepartment");
        employee.setBirthDate("01-01-2020");
        employee.setEmail("foo@bar.com");
        employee.setRole("CIO");
        employee.setTelephone("076 345 67 89");
        controller.doAction("edit", employee, null);
        Employee editedEmployee = controller.getEmployeeById(id);
        assertEquals("updateValueName", editedEmployee.getName());
        assertEquals("updateValueLastName", editedEmployee.getLastName());
        assertEquals("updateValueDepartment", editedEmployee.getDepartment());
        assertEquals("01-01-2020", editedEmployee.getBirthDate());
        assertEquals("foo@bar.com", editedEmployee.getEmail());
        assertEquals("CIO", editedEmployee.getRole());
        assertEquals("076 345 67 89", editedEmployee.getTelephone());
    }
    
    @Test
    public void testSearchValue() {
        EmployeeController controller = new EmployeeController();
        controller.doAction("search", null, "John");
        assertTrue(2 == controller.getSearchResult().size());
    }
    
    @Test
    public void testEmptySearchValue() {
        EmployeeController controller = new EmployeeController();
        controller.doAction("search", null, "");
        assertTrue(6 == controller.getSearchResult().size());
    }
}
