package me.freebuild.superspytx.secretword.events;

import java.util.logging.Level;

import me.freebuild.superspytx.secretword.Core;
import me.freebuild.superspytx.secretword.database.SecretPlayer;
import me.freebuild.superspytx.secretword.settings.Settings;
import me.freebuild.superspytx.secretword.utility.StringsUtility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

	public Core core = null;

	public PlayerListener(Core instance) {
		core = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void reallyLogin(PlayerLoginEvent e) {
		SecretPlayer player = new SecretPlayer(core, e.getPlayer().getName(), e
				.getAddress().toString().split(":")[0].replace("/", ""));
		core.getDB().secplayers.put(player.getName(), player);
		player.setBukkitPlayer(e.getPlayer());
		player.setInitialLocation(e.getPlayer().getLocation());
		// check registered
		player.setRegistered(core.getDB().userExists(player.getName()));

		// crap, this got removed
		if (core.getServer().getOnlineMode()) {
			player.setLoggedIn(core.getDB().ipMatches(player.getIP(),
					player.getName()));
		}
	}

	/*
	 * I prefer the hacky way of things.
	 */
	@EventHandler(priority = EventPriority.LOWEST)
	public void oncommand(PlayerCommandPreprocessEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onchat(PlayerChatEvent event) {
		if (core.getDB().secplayers.containsKey(event.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(event.getPlayer()
					.getName());
			String word = event.getMessage();
			if(player.isLoggedIn() && player.isRegistered()) {
				return;
			}
			
			
			if (!player.isLoggedIn() && !player.isRegistered()) {
				// register the player.
				// but first, get the word in our string.
				if (word.length() < Settings.minwordlength) {
					player.getPlayer().sendMessage(
							Settings.prefix
									+ "Your secret word must be longer than "
									+ Integer.toString(Settings.minwordlength)
									+ " characters.");
					event.setCancelled(true);
					return;
				}

				// good, register it and login.
				core.getDB().addLogin(player.getName(), word);
				player.setRegistered(true);
				player.setLoggedIn(true);
				event.setCancelled(true);
			} else if (!player.isLoggedIn()) {
				// process login
				
				if(!core.getDB().checkLogin(player.getName(), word)) {
					player.getPlayer().sendMessage(
							Settings.prefix + ChatColor.RED
									+ "Your secret word is incorrect!");
					event.setCancelled(true);
					return;
				}
				
				player.setLoggedIn(true);
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onjoin(PlayerJoinEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				// now lets check if registered or just stupid.
				if (!player.isRegistered()) {
					player.getPlayer()
							.sendMessage(
									Settings.prefix
											+ ChatColor.YELLOW
											+ "You must register a secret word! Type the secret word you want before continuing.");
				} else {
					player.getPlayer()
							.sendMessage(
									Settings.prefix
											+ ChatColor.RED
											+ "Please enter your assigned secret word.");
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void move(PlayerMoveEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			// if all goes well.
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			player.setBukkitPlayer(e.getPlayer());
			if (!player.isLoggedIn()) {
				player.teleportBack();
			}
		} else {
			// otherwise something really went wrong. perhaps derp?
			core.getLogger().log(
					Level.WARNING,
					"There's a serious problem, player "
							+ e.getPlayer().getName()
							+ " does not have a SecretPlayer record!");
			return;
		}
	}

}
