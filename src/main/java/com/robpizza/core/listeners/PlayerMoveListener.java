package com.robpizza.core.listeners;

import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Check if the VoidTeleport feature is on
        if(!ConfigHandler.getBaseConfig().getBoolean("void-teleport", false)) {
            return;
        }

        // Get the player
        Player player = event.getPlayer();

        // Check if the world is the protected world
        String protectedWorld = ConfigHandler.getBaseConfig().getString("protected-world", "");
        if (protectedWorld.equalsIgnoreCase(player.getWorld().getName())) {

            // Check PlayerHeight
            if(player.getLocation().getY() <= ConfigHandler.getBaseConfig().getInt("void-threshold")) {
                player.performCommand("spawn");
            }
        }
    }
}
