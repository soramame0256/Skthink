package com.github.soramame0256;

import com.github.soramame0256.Skthink;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerLoginEvent;

public class Skriptadditions implements Listener {
	Skthink m = Skthink.getInstance();
	private Integer playercount = m.getServer().getOnlinePlayers().size();
	public void onPlayerLogin(PlayerLoginEvent e) {
		Boolean enable = m.getConfig().getBoolean("effect.connectionlimit.enable");
		if(enable) {
			if(m.getConfig().getInt("effect.connectionlimit.count") < playercount) {
				if(!e.getPlayer().isOp()) {
					e.getPlayer().kickPlayer(m.getConfig().getString("effect.connectionlimit.message"));
				}
			}
		}
	}
}
