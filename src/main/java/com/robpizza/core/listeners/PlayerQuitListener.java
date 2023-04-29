package com.robpizza.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.robpizza.core.plugin.ConfigHandler.__;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Get the player
        Player player = event.getPlayer();

        // Get the configured quit message
        String quitAnnounce = __("leave-message");

        // Empty string check
        if (quitAnnounce.isEmpty()) {
            event.setQuitMessage(null);
        }

        // Replace some placeholders
        quitAnnounce = quitAnnounce.replace("{player}", player.getName());

        // Set the new message
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', quitAnnounce));
    }
}
