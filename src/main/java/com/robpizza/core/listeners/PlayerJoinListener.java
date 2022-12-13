package com.robpizza.core.listeners;

import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String joinAnnounce = ConfigHandler.getBaseConfig().getString("announcements.join", "");

        if (joinAnnounce.isEmpty()) {
            event.setJoinMessage(null);
        }

        joinAnnounce = joinAnnounce.replace("{player}", player.getName());
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinAnnounce));
    }
}
