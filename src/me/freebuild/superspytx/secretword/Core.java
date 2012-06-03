package me.freebuild.superspytx.secretword;

import me.freebuild.superspytx.secretword.database.DatabaseCore;
import me.freebuild.superspytx.secretword.events.CoreEvents;
import me.freebuild.superspytx.secretword.events.PlayerEvents;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

	// cores
	private DatabaseCore dbcore = null;
	private CoreEvents clisten = null;
	private PlayerEvents plisten = null;
	
	public void onDisable() {

	}

	public void onEnable() {
		dbcore = new DatabaseCore(this);
		clisten = new CoreEvents(this);
		plisten = new PlayerEvents(this);
		getServer().getPluginManager().registerEvents(clisten, this);
		getServer().getPluginManager().registerEvents(plisten, this);
	}
	
	
	public DatabaseCore getDB() {
		return dbcore;
	}

}
