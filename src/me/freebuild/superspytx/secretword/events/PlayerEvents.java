package me.freebuild.superspytx.secretword.events;

import java.util.logging.Level;

import me.freebuild.superspytx.secretword.Core;
import me.freebuild.superspytx.secretword.database.SecretPlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

public class PlayerEvents implements Listener {
	
	public Core core = null;
	
	public PlayerEvents(Core instance) {
		core = instance;
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void e(PlayerMoveEvent e) {
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
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerBedEnterEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerBucketEmptyEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerBucketFillEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerCommandPreprocessEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerDropItemEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerInteractEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerFishEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerGameModeChangeEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerInteractEntityEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(InventoryOpenEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerPickupItemEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerPortalEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerShearEntityEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerToggleSneakEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerToggleSprintEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerVelocityEvent e) {
		if (core.getDB().secplayers.containsKey(e.getPlayer().getName())) {
			SecretPlayer player = core.getDB().secplayers.get(e.getPlayer()
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	
}
