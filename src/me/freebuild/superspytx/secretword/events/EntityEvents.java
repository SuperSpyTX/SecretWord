package me.freebuild.superspytx.secretword.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import me.freebuild.superspytx.secretword.Core;
import me.freebuild.superspytx.secretword.database.SecretPlayer;

public class EntityEvents implements Listener {

	public Core core = null;

	public EntityEvents(Core instance) {
		core = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityDamageEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER))
			return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e2(EntityDamageEvent es) {
		if (!es.getEntityType().equals(EntityType.PLAYER))
			return;
		EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) es;
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player mainplayer = (Player) e.getDamager();
			if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
				SecretPlayer player = core.getDB().secplayers.get(mainplayer
						.getName());
				if (!player.isLoggedIn()) {
					es.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityBreakDoorEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER))
			return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityDamageByEntityEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER))
			return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityDamageByBlockEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER))
			return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityShootBowEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER))
			return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(EntityTargetLivingEntityEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER))
			return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer
					.getName());
			if (!player.isLoggedIn()) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void e(PlayerDeathEvent e) {
		if (!e.getEntityType().equals(EntityType.PLAYER))
			return;
		Player mainplayer = (Player) e.getEntity();
		if (core.getDB().secplayers.containsKey(mainplayer.getName())) {
			SecretPlayer player = core.getDB().secplayers.get(mainplayer
					.getName());
			if (!player.isLoggedIn()) {
				e.setKeepLevel(true);
				e.setDroppedExp(0);
			}
		}
	}
}
