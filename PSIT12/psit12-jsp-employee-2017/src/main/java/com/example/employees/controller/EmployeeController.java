package com.example.employees.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.employees.domain.Employee;
import com.example.employees.repository.EmployeeRepository;
import com.example.employees.repository.InMemoryEmployeeRepository;

public class EmployeeController {
	private EmployeeRepository repository;
	private List<Employee> searchResult;

	public EmployeeController() {
		this.repository = new InMemoryEmployeeRepository();
		this.repository.init();
		this.searchResult = new ArrayList<>();
	}

	public void doAction(String action, Employee employee, String searchText) {

		if (null != action) {

			switch (action) {
			case "add":
				this.repository.addEmployee(employee);
				break;
			case "edit":
				this.repository.updateEmployee(employee);
				break;
			case "remove":
				this.repository.removeEmployee(employee.getId());
				break;
			case "search":
				if (searchText != "") {
					this.searchResult = this.searchByName(searchText);
				} else {
					this.searchResult = this.getAllEmployees();
				}
				break;
			default:
				// do nothing
			}
		}
	}

	public List<Employee> getAllEmployees() {
		return this.repository.getAllEmployees();
	}

	public Employee getEmployeeById(long id) {
		return this.repository.getEmployeeById(id);
	}

	public List<Employee> getSearchResult() {
		return this.searchResult;
	}

	/**
	 * Durschnittsalter aller Mitarbeiter
	 * 
	 * @return
	 */
	public int getAvgEmployeeAge() {
		// TODO: student
		int average = 0;
		for (Employee employee : repository.getAllEmployees()) {
			average += employee.getAge(); // Age wurde in der Klasse Employee erfasst, mit entspr. Setter und Getter

		}
		return average / repository.getAllEmployees().size();
	}

	/**
	 * Anzahl Mitarbeiter pro Rolle
	 * 
	 * @return
	 */
	public Map<String, Integer> getCountPerRole() {
		// TODO: student
		Map<String, Integer> list = new HashMap<String, Integer>();

		for (Employee employee : repository.getAllEmployees()) {
			String role = employee.getRole();
			if (list.containsKey(role)) {
				int counter = list.get(role);
				list.replace(role, ++counter);
			} else {
				list.put(role, 1);
			}
		}
		return list;
	}

	/**
	 * Anzahl Departements
	 * 
	 * @return
	 */
	public int countDepartments() {
		Map<String, Integer> list = new HashMap<String, Integer>();
		for (Employee employee : this.getAllEmployees()) {
			String department = employee.getDepartment();
			if (list.containsKey(department)) {
				int i = list.get(department);
				list.replace(department, ++i);
			} else {
				list.put(department, 1);
			}
		}
		return list.size();
	}

	private List<Employee> searchByName(String name) {
		Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getName)
				.thenComparing(Employee::getLastName);
		List<Employee> result = this.repository.getAllEmployees().stream()
				.filter(e -> e.getName().equalsIgnoreCase(name) || e.getLastName().equalsIgnoreCase(name))
				.sorted(groupByComparator).collect(Collectors.toList());
		return result;
	}
}
