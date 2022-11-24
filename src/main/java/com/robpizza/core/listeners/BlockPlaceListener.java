package com.robpizza.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static com.robpizza.core.plugin.ConfigHandler.getBaseConfig;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        // Check if the 'protected-world' setting is configured
        if (getBaseConfig().getString("protected-world") != null) {
            Player player = event.getPlayer();

            // Check if the player has the bypass permission
            if (!player.hasPermission("core.bypass.protection.place") || !player.isOp()) {

                // Check if the World is loaded to prevent "IllegalArgumentException"
                if (player.getLocation().isWorldLoaded()) {
                    boolean cancel = player.getLocation().getWorld().getName().equalsIgnoreCase(getBaseConfig().getString("protected-world"));
                    event.setCancelled(cancel);
                }
            }
        }
    }
}
