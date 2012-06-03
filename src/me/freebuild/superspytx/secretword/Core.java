package me.freebuild.superspytx.secretword;

import me.freebuild.superspytx.secretword.database.DatabaseCore;
import me.freebuild.superspytx.secretword.events.CoreEvents;
import me.freebuild.superspytx.secretword.events.EntityEvents;
import me.freebuild.superspytx.secretword.events.PlayerEvents;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

	// cores
	private DatabaseCore dbcore = null;
	private CoreEvents clisten = null;
	private PlayerEvents plisten = null;
	private EntityEvents elisten = null;
	
	public void onDisable() {

	}

	public void onEnable() {
		dbcore = new DatabaseCore(this);
		clisten = new CoreEvents(this);
		plisten = new PlayerEvents(this);
		elisten = new EntityEvents(this);
		getServer().getPluginManager().registerEvents(clisten, this);
		getServer().getPluginManager().registerEvents(plisten, this);
		getServer().getPluginManager().registerEvents(elisten, this);
	}
	
	
	public DatabaseCore getDB() {
		return dbcore;
	}

}
