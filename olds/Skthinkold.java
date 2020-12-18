package com.github.soramame0256;

import com.github.soramame0256.SkthinkSQL;

import ch.njol.skript.*;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import com.mysql.jdbc.util.*;

import com.mysql.jdbc.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;

import org.bukkit.plugin.java.*;
@SuppressWarnings("unused")

public class Skthinkold extends JavaPlugin {
	
	static Skthinkold instance;
	SkriptAddon addon;
	public void onEnable() {
		instance = this;
		addon = Skript.registerAddon(this);
		try {
			addon.loadClasses("com.github.soramame0256", "skthink");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.saveDefaultConfig();
		//Database
		this.getLogger().info("connecting to database...");
//		SkthinkSQL instance1 = SkthinkSQL.getInstance();
		setup();
		this.getLogger().info("Skthink is activated!");
	}
	public static Skthinkold getInstance() {
		return instance;
	}
	public void setup() {
		PreparedStatement pstmt = null;
		try {
			String request = "CREATE DATABASE IF NOT EXISTS skthink";
			pstmt = getsqlconnection().prepareStatement(request);
			pstmt.executeUpdate();
			String request1 = "CREATE TABLE IF NOT EXISTS skthink.memory";
			pstmt = getsqlconnection().prepareStatement(request1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			this.getLogger().info("null");
			e.printStackTrace();
		}
	}
	public Connection getsqlconnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + this.getConfig().getString("database.path"),
					this.getConfig().getString("database.username"),
					this.getConfig().getString("database.password")
			);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			this.getLogger().info("SQLException occurred 1");
			return null;
		}
		
	}
	public String dataget(String column, String tablename) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String ret = null;
		String request = "SELECT " + column + " FROM " + tablename;
		try {
			ps = getsqlconnection().prepareStatement(request);
			rs = ps.executeQuery();
			ret = rs.getString(column);
			getsqlconnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
		
	}
}