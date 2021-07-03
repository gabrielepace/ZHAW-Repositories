package com.example.employees.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.employees.domain.Employee;

public class H2EmployeeRepository implements EmployeeRepository {

	List<Employee> employeeList = new ArrayList<Employee>();

	@Override
	public long addEmployee(Employee employee) {
		// TODO
		for (Employee emp : employeeList) {
			employeeList.add(employee);
		}
		return employee.getId();
	}

	private void establishH2DBConnection() {
		// TODO
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO
		for (Employee emp : employeeList) {
			employeeList.size();
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO
		return null;
	}

	@Override
	public void init() {
		this.loadH2Driver();
		this.establishH2DBConnection();
		this.setUpH2Database();
	}

	private void loadH2Driver() {
		// TODO
	}

	@Override
	public boolean removeEmployee(long id) {
		// TODO
		Iterator<Employee> iter = employeeList.iterator();

		for (Employee emp : employeeList) {
			if (id == emp.getId()) {
				if (iter.hasNext()) {
					iter.remove();
					return true;
				} else {
					return false;
				}
			}

		}

		return false;
	}

	private void setUpH2Database() {

		/*
		 * DUMMY Daten
		 *
		 * statement. execute("CREATE TABLE PERSON(id int primary key AUTO_INCREMENT,
		 * firstName varchar(255), lastName varchar(255), userName varchar(255),
		 * birthday DATE," +
		 * "position varchar(100), dept varchar(100), email varchar(255));"); String
		 * insertStatement
		 * ="INSERT INTO PERSON(firstName,lastName,userName,birthday,position,dept,email) VALUES "
		 * ; statement.execute(insertStatement+
		 * "('John','Smith','john.smith','1980-12-12','Manager','Sales','john.smith@abc.com');"
		 * ); statement.execute(insertStatement+
		 * "('Laura','Adams','laura.smith','1979-11-02','Manager','IT','laura.adams@abc.com');"
		 * ); statement.execute(insertStatement+
		 * "('Peter','Williams','peter.williams','1966-10-22','Coordinator','HR','peter.williams@abc.com');"
		 * ); statement.execute(insertStatement+
		 * "('Joana','Sanders','joana.sanders','1976-11-11','Manager','Marketing','joana.sanders@abc.com');"
		 * ); statement.execute(insertStatement+
		 * "('John','Drake','john.drake','1988-08-18','Coordinator','Finance','john.drake@abc.com');"
		 * ); statement.execute(insertStatement+
		 * "('Samuel','Williams','samuel.williams','1985-03-22','Coordinator','Finance','samuel.williams@abc.com');"
		 * );
		 *
		 * statement.close();
		 */

	}

	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO
		return false;
	}

}
