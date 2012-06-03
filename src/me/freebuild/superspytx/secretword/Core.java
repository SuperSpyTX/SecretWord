package me.freebuild.superspytx.secretword;

import me.freebuild.superspytx.secretword.database.DatabaseCore;
import me.freebuild.superspytx.secretword.events.PlayerListener;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

	// cores
	private DatabaseCore dbcore = null;
	private PlayerListener plisten = null;
	
	public void onDisable() {

	}

	public void onEnable() {
		dbcore = new DatabaseCore(this);
		plisten = new PlayerListener(this);
		getServer().getPluginManager().registerEvents(plisten, this);
	}
	
	
	public DatabaseCore getDB() {
		return dbcore;
	}

}
