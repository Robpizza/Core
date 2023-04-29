package com.robpizza.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static com.robpizza.core.plugin.ConfigHandler.getBaseConfig;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        // Check if the 'protected-world' setting is configured
        if (getBaseConfig().getString("protected-world") != null) {

            // Check if the damaged entity is a player
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();

                // Check if the player is in a protected world
                if(player.getWorld().getName().equalsIgnoreCase(getBaseConfig().getString("protected-world"))) {
                    // Cancel damage event if the cause was fall
                    event.setCancelled(event.getCause() == EntityDamageEvent.DamageCause.FALL);
                }
            }
        }
    }
}
