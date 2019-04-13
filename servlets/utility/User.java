package com.proj.servlets.utility;

public class User {
	private static int employeeId;
	private static int managerId;
	private static boolean isEmployee;
	private static boolean isManager;
	
	public static void setEmployee(int employeeId, boolean isManager) {
		User.employeeId = employeeId;
		User.isManager = isManager;
	}
	
	public static void setManager(int managerId, boolean isEmployee) {
		User.managerId = managerId;
		User.isEmployee = isEmployee;
	}
	
	public static int getEmployeeId() {
		return employeeId;
	}
	
	public static int getManagerId() {
		return managerId;
	}
	
	public static boolean isManager() {
		return isManager;
	}
	
	public static boolean isEmployee() {
		return isEmployee;
	}
	
	public static boolean isEmployeeNull() {
		return employeeId < 0;
	}
	
	public static boolean isManagerNull() {
		return managerId < 0;
	}
	
	public static void setEmployeeNull() {
		employeeId = -1;
		isManager = false;
	}
	
	public static void setManagerNull() {
		managerId = -1;
		isEmployee = false;
	}
	
}
