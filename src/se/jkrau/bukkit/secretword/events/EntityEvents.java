package se.jkrau.bukkit.secretword.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import se.jkrau.bukkit.secretword.SecretWord;
import se.jkrau.bukkit.secretword.database.SecretPlayer;

public class EntityEvents implements Listener {
	
	public SecretWord core = null;
	
	public EntityEvents(SecretWord instance) {
		core = instance;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityDamageEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER)) return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e2(EntityDamageEvent es) {
		if (!es.getEntityType().equals(EntityType.PLAYER)) return;
		try {
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) es;
			if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
				Player mainplayer = (Player) e.getDamager();
				if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
					SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
					if (!player.isLoggedIn()) {
						es.setCancelled(true);
					}
				}
			}
		} catch (Exception e) {
			// ignore.
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityBreakDoorEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER)) return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityDamageByEntityEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER)) return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityDamageByBlockEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER)) return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityShootBowEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER)) return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityTargetLivingEntityEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER)) return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerDeathEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER)) return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer.getName());
			if (!player.isLoggedIn()) {
				e.setKeepLevel(true);
				e.setDroppedExp(0);
			}
		}
	}
}
