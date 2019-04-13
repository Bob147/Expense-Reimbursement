package com.proj.dao;

import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proj.model.Manager;
import com.proj.servlets.utility.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public List<Manager> getManagers() {
		List<Manager> managerList = new ArrayList<>();
		String sql = "SELECT * FROM MANAGER_TABLE";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("MANAGER_ID");
				String username = rs.getString("MANAGER_USERNAME");
				String password = rs.getString("MANAGER_PASSWORD");
				String firstname = rs.getString("FIRSTNAME");
				String lastname = rs.getString("LASTNAME");

				Manager m = new Manager();
				m.setId(id);
				m.setFirstname(firstname);
				m.setLastname(lastname);
				m.setUsername(username);
				m.setPassword(password);
				managerList.add(m);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return managerList;
	}

	@Override
	public Manager getManagerById(int id) {
		Manager man = null;
		try (Connection con = ConnectionUtil.getConnection();) {
			man = getManagerById(id, con);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return man;
	}
	
	@Override
	public Manager getManagerByUsername(String username) {
		Manager man = null;
		String sql = "SELECT * FROM MANAGER_TABLE WHERE MANAGER_USERNAME = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("MANAGER_ID");
				String firstname = rs.getString("FIRSTNAME");
				String lastname = rs.getString("LASTNAME");
				String password = rs.getString("MANAGER_PASSWORD");

				man = new Manager();
				man.setId(id);
				man.setFirstname(firstname);
				man.setLastname(lastname);
				man.setUsername(username);
				man.setPassword(password);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return man;
	}

	@Override
	public Manager getManagerById(int id, Connection con) {
		Manager man = null;
		String sql = "SELECT * FROM MANAGER_TABLE WHERE MANAGER_ID = ?";
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String firstname = rs.getString("FIRSTNAME");
				String lastname = rs.getString("LASTNAME");
				String username = rs.getString("MANAGER_USERNAME");
				String password = rs.getString("MANAGER_PASSWORD");

				man = new Manager();
				man.setId(id);
				man.setFirstname(firstname);
				man.setLastname(lastname);
				man.setUsername(username);
				man.setPassword(password);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return man;
	}

	

}
