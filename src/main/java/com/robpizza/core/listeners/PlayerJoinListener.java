package com.robpizza.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.robpizza.core.plugin.ConfigHandler.__;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Get the player
        Player player = event.getPlayer();

        // Get the configured join message
        String joinAnnounce = __("join-message");

        // Empty string check
        if (joinAnnounce.isEmpty()) {
            event.setJoinMessage(null);
        }

        // Replace some placeholders
        joinAnnounce = joinAnnounce.replace("{player}", player.getName());

        // Set the new message
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinAnnounce));
    }
}
