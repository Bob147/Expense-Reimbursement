package com.proj.dao;

import java.math.BigDecimal;

import java.sql.Connection;
import java.util.List;

import com.proj.model.Request;

public interface RequestDao {
	Request getRequestById(int id);
	BigDecimal getSumByType(String type);
	Request getRequestById(int id, Connection con);
	List<Request> getRequestsByEmployeeId(int managerId);
	List<Request> getRequest(int employeeId);
	List<Request> getPendingRequestsByEmployeeId(int employeeId);
	List<Request> getPendingRequest(int managerId);
	List<Request> getResolvedRequests(int managerId);
	List<Request> getResolvedRequestsByEmployeeId(int employeeId);
	void updateRequest(Request r);
	void addRequest(Request r);

}
