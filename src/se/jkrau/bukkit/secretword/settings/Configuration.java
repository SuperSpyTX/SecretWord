package se.jkrau.bukkit.secretword.settings;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import se.jkrau.bukkit.secretword.SecretWord;

public class Configuration {
	
	// primary keys
	public static Logger log = Logger.getLogger("Minecraft");
	public static String logPrefix = "[SecretWord] ";
	public static String prefix = "[" + ChatColor.GOLD + "SecretWord" + ChatColor.WHITE + "] ";
	public static int minwordlength = 9;
	public static int maxwordlength = 20;
	public static int maxloginattempts = 3;
	public static boolean enableByPermission = false;
	public static boolean hideJoinNotifications = true;
	public static boolean onlineModeBehavior = true;
	public static boolean blockLoginFromOtherLocation = true;
	public static boolean notifyPlayerOfReset = true; // good choice to do.
	public static boolean debug = false;
	public static String version = "";
	
	// private
	private static FileConfiguration fc;
	
	public static void reloadConfiguration() {
		fc = YamlConfiguration.loadConfiguration(new File(SecretWord.instance.getDataFolder(), "config.yml"));
		fc.addDefault("SecretWord.Prefix", prefix);
		fc.addDefault("SecretWord.MinWordLength", minwordlength);
		fc.addDefault("SecretWord.MaxLoginAttempts", maxloginattempts);
		fc.addDefault("SecretWord.EnableByPermission", enableByPermission);
		fc.addDefault("SecretWord.OnlineModeBehavior", onlineModeBehavior);
		fc.addDefault("SecretWord.BlockLoginFromOtherLocation", blockLoginFromOtherLocation);
		fc.addDefault("SecretWord.NotifyPlayerWhenReset", notifyPlayerOfReset);
		fc.addDefault("SecretWord.HideJoinNotifications", hideJoinNotifications);
		fc.addDefault("SecretWord.Debug", debug);
		fc.options().copyDefaults(true);
		try {
			fc.save(new File(SecretWord.instance.getDataFolder(), "config.yml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fc = YamlConfiguration.loadConfiguration(new File(SecretWord.instance.getDataFolder(), "config.yml"));
		prefix = fc.getString("SecretWord.Prefix");
		minwordlength = fc.getInt("SecretWord.MinWordLength");
		maxloginattempts = fc.getInt("SecretWord.MaxLoginAttempts");
		enableByPermission = fc.getBoolean("SecretWord.EnableByPermission");
		onlineModeBehavior = fc.getBoolean("SecretWord.OnlineModeBehavior");
		hideJoinNotifications = fc.getBoolean("SecretWord.HideJoinNotifications");
		notifyPlayerOfReset = fc.getBoolean("SecretWord.NotifyPlayerWhenReset");
		blockLoginFromOtherLocation = fc.getBoolean("SecretWord.BlockLoginFromOtherLocation");
		debug = fc.getBoolean("SecretWord.Debug");
	}
	
	public static void log(String e) {
		if (debug) SecretWord.instance.getLogger().log(Level.INFO, "[DEBUG] " + e);
	}
}
