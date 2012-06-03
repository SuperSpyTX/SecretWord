package me.freebuild.superspytx.secretword.settings;

import java.util.logging.Logger;

import org.bukkit.ChatColor;

public class Settings {
	
	// primary keys
	public static Logger log = Logger.getLogger("Minecraft");
	public static String logPrefix = "[SecretWord] ";
	public static String prefix = "[" + ChatColor.GOLD + "SecretWord" + ChatColor.WHITE + "] ";
	public static int minwordlength = 9;
}
