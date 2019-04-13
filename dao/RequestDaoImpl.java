package com.proj.dao;

import java.io.IOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proj.model.Employee;
import com.proj.model.Manager;
import com.proj.model.Request;
import com.proj.servlets.utility.ConnectionUtil;

public class RequestDaoImpl implements RequestDao {
	
	@Override
	public Request getRequestById(int id) {
		Request req = null;
		String sql = "SELECT * FROM REQUEST WHERE REQUEST_ID = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				req = populateRequest(rs, con);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return req;
	}

	@Override
	public BigDecimal getSumByType(String type) {
		BigDecimal amount = new BigDecimal('0');
		String sql = "SELECT SUM(AMOUNT) FROM REQUEST WHERE TYPE1 = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, type);
			rs = ps.executeQuery();
			while (rs.next()) {
				amount = rs.getBigDecimal(1);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return amount;
	}

	@Override
	public Request getRequestById(int id, Connection con) {
		Request req = null;
		String sql = "SELECT * FROM REQUEST WHERE REQUEST_ID = ?";
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String type = rs.getString("TYPE1");
				String status = rs.getString("STATUS");
				String decision = rs.getString("DECISION");
				BigDecimal amount = rs.getBigDecimal("AMOUNT");
				int employeeId = rs.getInt("EMPLOYEE_ID");
				int managerId = rs.getInt("MANAGER_ID");
				EmployeeDao ed = new EmployeeDaoImpl();
				Employee e = ed.getEmployeeById(employeeId, con);
				ManagerDao md = new ManagerDaoImpl();
				Manager m = md.getManagerById(managerId, con);

				req = new Request();
				req.setId(id);
				req.setType(type);
				req.setStatus(status);
				req.setDecision(decision);
				req.setAmount(amount);
				req.setEmployee(e);
				req.setManager(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return req;
	}

	private List<Request> getRequestsByManagerId(int managerId, String sql) {
		List<Request> requests = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			rs = ps.executeQuery();
			while (rs.next()) {
				Request req = populateRequest(rs, con);
				requests.add(req);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return requests;
	}

	@Override
	public List<Request> getRequest(int managerId) {
		return getRequestsByManagerId(managerId, "SELECT * FROM REQUEST");
	}

	@Override
	public List<Request> getPendingRequest(int managerId) {
		return getRequestsByManagerId(managerId, "SELECT * FROM REQUEST WHERE STATUS = 'pending'");
	}

	@Override
	public List<Request> getResolvedRequests(int managerId) {
		return getRequestsByManagerId(managerId, "SELECT * FROM REQUEST WHERE STATUS = 'resolved'");
	}

	private List<Request> getRequestsByEmployeeId(int employeeId, String sql) {
		List<Request> requests = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Request req = populateRequest(rs, con);
				requests.add(req);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return requests;
	}

	@Override
	public List<Request> getRequestsByEmployeeId(int employeeId) {
		return getRequestsByEmployeeId(employeeId, "SELECT * FROM REQUEST WHERE EMPLOYEE_ID = ?");
	}

	@Override
	public List<Request> getPendingRequestsByEmployeeId(int employeeId) {
		String sql = "SELECT * FROM REQUEST WHERE EMPLOYEE_ID = ? AND STATUS = 'pending' ";
		return getRequestsByEmployeeId(employeeId, sql);
	}

	@Override
	public List<Request> getResolvedRequestsByEmployeeId(int employeeId) {
		String sql = "SELECT * FROM REQUEST WHERE EMPLOYEE_ID = ? AND NOT STATUS = 'pending' ";
		return getRequestsByEmployeeId(employeeId, sql);
	}

	@Override
	public void addRequest(Request r) {
		r.setId(getNextId());
		String sql = "INSERT INTO REQUEST (REQUEST_ID, TYPE1, STATUS, DECISION, AMOUNT, EMPLOYEE_ID) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, r.getId());
			ps.setString(2, r.getType());
			ps.setString(3, r.getStatus());
			ps.setString(4, r.getDecision());
			ps.setBigDecimal(5, r.getAmount());
			ps.setInt(6, r.getEmployee().getId());
			ps.executeQuery();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private int getNextId() {
		int nextId = -1;
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			rs = s.executeQuery("SELECT MAX(REQUEST_ID) FROM REQUEST");
			if (rs.next()) {
				if (nextId == 0) {
					nextId = 1;
				} else {
					nextId = rs.getInt(1) + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return nextId;
	}

	@Override
	public void updateRequest(Request r) {
		String sql = "UPDATE REQUEST SET REQUEST_ID = ?, TYPE1 = ?, STATUS = ?, DECISION = ?, AMOUNT = ?, EMPLOYEE_ID = ?, MANAGER_ID = ? WHERE REQUEST_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, r.getId());
			ps.setString(2, r.getType());
			ps.setString(3, r.getStatus());
			ps.setString(4, r.getDecision());
			ps.setBigDecimal(5, r.getAmount());
			ps.setInt(6, r.getEmployee().getId());
			ps.setInt(7, r.getManager().getId());
			ps.setInt(8, r.getId());
			ps.executeQuery();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private Request populateRequest(ResultSet rs, Connection con) throws SQLException {
		int requestId = rs.getInt("REQUEST_ID");
		String type = rs.getString("TYPE1");
		String status = rs.getString("STATUS");
		String decision = rs.getString("DECISION");
		BigDecimal amount = rs.getBigDecimal("AMOUNT");
		int employeeId = rs.getInt("EMPLOYEE_ID");
		int managerId = rs.getInt("MANAGER_ID");
		EmployeeDao ed = new EmployeeDaoImpl();
		Employee e = ed.getEmployeeById(employeeId, con);
		ManagerDao ad = new ManagerDaoImpl();
		Manager m = ad.getManagerById(managerId, con);

		Request r = new Request();
		r.setId(requestId);
		r.setType(type);
		r.setStatus(status);
		r.setDecision(decision);
		r.setAmount(amount);
		r.setEmployee(e);
		r.setManager(m);
		return r;
	}
	
	

}
