package com.github.soramame0256;


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

public class Skthink extends JavaPlugin {
	
	static Skthink instance;
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
		
		this.getLogger().info("Skthink is activated!");
	}
	public static Skthink getInstance() {
		return instance;
	}
}