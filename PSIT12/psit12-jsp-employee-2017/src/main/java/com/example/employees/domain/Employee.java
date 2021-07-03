/* Copyright 2015 Oracle and/or its affiliates. All rights reserved. */

package com.example.employees.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author luperalt
 */
public class Employee {

	private long id;
	private int age;
	private String name;
	private String lastName;
	private String birthDate;
	private String role;
	private String department;
	private String email;
	private String userName;
	private String telephone;

	public Employee() {
		this.name = "";
		this.lastName = "";
		this.birthDate = "";
		this.age = 0;
		this.role = "";
		this.department = "";
		this.email = "";
		this.userName = "";
		this.telephone = "";
	}

	public Employee(ResultSet rs) throws SQLException {
		this.name = rs.getString("firstName");
		this.lastName = rs.getString("lastName");
		this.birthDate = new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("birthday"));
		this.age = rs.getInt(age);
		this.role = rs.getString("position");
		this.department = rs.getString("dept");
		this.email = rs.getString("email");
		this.id = rs.getInt("id");
		this.userName = rs.getString("userName");
		this.telephone = rs.getString("telephone");
	}

	public Employee(String name, String lastName, String userName, String birthDate, String role, String department,
			String email, String telephone) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.birthDate = birthDate;
		this.role = role;
		this.department = department;
		this.email = email;
		this.telephone = telephone;
	}

	public Employee(String name, String lastName, String userName, String birthDate, String role, String department,
			String email, String telephone, long id) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.birthDate = birthDate;
		this.role = role;
		this.department = department;
		this.email = email;
		this.telephone = telephone;
		this.id = id;

	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public int getAge() {
		return age;
	}

	public String getDepartment() {
		return this.department;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelephone() {
		return telephone;
	}

	public long getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getName() {
		return this.name;
	}

	public String getRole() {
		return this.role;
	}

	public String getUserName() {
		return userName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + this.id + ", name=" + this.name + ", lastName=" + this.lastName + ", userName="
				+ this.userName + ", birthDate=" + this.birthDate + ", role=" + this.role + ", department="
				+ this.department + ", email=" + this.email + ", telephone=" + this.telephone + '}';
	}
}
