package com.github.soramame0256;

import ch.njol.skript.*;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import com.mysql.jdbc.*;
import java.io.IOException;

import org.bukkit.plugin.java.*;
@SuppressWarnings("unused")

public class Skthink extends JavaPlugin {
	
	Skthink instance;
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
		String database = this.getConfig().getString("database.path");
		
		this.getLogger().info("Skthink is activated!");
	}
}
