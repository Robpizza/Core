package com.robpizza.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static com.robpizza.core.plugin.ConfigHandler.getBaseConfig;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Check if there even is a world configured
        if (getBaseConfig().getStringList("instant-respawn").size() > 0) {
            Player player = event.getEntity();

            // Check if the World is loaded to prevent "IllegalArgumentException"
            if (player.getLocation().isWorldLoaded() && player.getLocation().getWorld() != null) {

                // Check if instant respawn is enabled
                if(getBaseConfig().getStringList("instant-respawn").contains(player.getLocation().getWorld().getName())) {
                    player.spigot().respawn();
                }
            }
        }
    }
}
