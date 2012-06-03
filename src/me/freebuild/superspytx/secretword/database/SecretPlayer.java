package me.freebuild.superspytx.secretword.database;

import me.freebuild.superspytx.secretword.Core;
import me.freebuild.superspytx.secretword.settings.Settings;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SecretPlayer {
	
	private Core core = null;
	private String playername = null;
	private String ipaddress = null;
	private Player bukkitplayer = null;
	private boolean loggedIn = false;
	private boolean registered = false;
	private boolean hasCreativeMode = false;
	private int loginattempts = 0;
	private Location initialLocation = null;
	
	public SecretPlayer(Core instance, String mcuser, String ip) {
		core = instance;
		playername = mcuser;
		ipaddress = ip;
	}
	
	public void setLoggedIn(boolean e) {
		loggedIn = e;
		if(loggedIn) {
			bukkitplayer.sendMessage(Settings.prefix + ChatColor.GREEN + "You've successfully logged in! Enjoy!");
		}
		
		if(hasCreativeMode) {
			bukkitplayer.setGameMode(GameMode.CREATIVE);
		}
	}
	
	public void setHasCreative(boolean e) {
		hasCreativeMode = e;
		if(hasCreativeMode) {
			bukkitplayer.setGameMode(GameMode.SURVIVAL);
		}
	}
	
	public void setRegistered(boolean e) {
		registered = e;
	}
	
	public boolean triggerAttempt() {
		loginattempts++;
		
		if(loginattempts > Settings.maxloginattempts) {
			bukkitplayer.kickPlayer(Settings.logPrefix + "Too many login attempts!");
			return true;
		}
		return false;
	}
	
	public void setBukkitPlayer(Player e) {
		if(bukkitplayer == null) {
			bukkitplayer = e;
		}
	}
	
	public void setInitialLocation(Location loc) {
		if(initialLocation == null) {
			initialLocation = loc;
		}
	}
	
	public void teleportBack() {
		bukkitplayer.teleport(initialLocation);
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public boolean isRegistered() {
		return registered;
	}
	
	public String getName() {
		return playername;
	}
	
	public String getIP() {
		return ipaddress;
	}
	
	public Player getPlayer() {
		return bukkitplayer;
	}
	
	public Location getLocation() {
		return initialLocation;
	}

}
