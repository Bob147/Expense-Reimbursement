package com.proj.dao;

import java.sql.Connection;

import java.util.List;

import com.proj.model.Employee;

public interface EmployeeDao {
	public int createEmployee(Employee employee);
	Employee getEmployeeByUsername(String username);
	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	Employee getEmployeeById(int id, Connection con);
	void updateEmployee(Employee e);
}
