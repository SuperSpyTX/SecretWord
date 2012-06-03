package me.freebuild.superspytx.secretword.events;

import me.freebuild.superspytx.secretword.Core;
import me.freebuild.superspytx.secretword.database.SecretPlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.inventory.*;

public class PlayerEvents implements Listener {
	
	public Core core = null;
	
	public PlayerEvents(Core instance) {
		core = instance;
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
	public void e(PlayerBucketEvent e) {
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
	public void e(PlayerTeleportEvent e) {
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
