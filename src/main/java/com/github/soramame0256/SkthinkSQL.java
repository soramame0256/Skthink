package com.github.soramame0256;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.plugin.java.*;

public class SkthinkSQL extends JavaPlugin {
	private static SkthinkSQL instance;
	public boolean setup() {
		instance = this;
		PreparedStatement pstmt = null;
		try {
			Connection con = getconnection();
			String request = "CREATE DATABASE IF NOT EXISTS skthink";
			pstmt = con.prepareStatement(request);
			pstmt.executeUpdate();
			String request1 = "CREATE TABLE IF NOT EXISTS skthink.memory";
			pstmt = con.prepareStatement(request1);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static SkthinkSQL getInstance() {
		return instance;
	}
	public Connection getconnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(
				"jdbc:mysql://" + this.getConfig().getString("database.path"),
				this.getConfig().getString("database.username"),
				this.getConfig().getString("database.password")
			);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			getLogger().info("SQLException occurred 1");
			return null;
		}
		
	}
	public String dataget(String column, String tablename) {
		Connection con = this.getconnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String ret = null;
		String request = "SELECT " + column + " FROM " + tablename;
		try {
			ps = con.prepareStatement(request);
			rs = ps.executeQuery();
			ret = rs.getString(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
		
	}
}
