package com.robpizza.core.listeners;

import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        // Get the configured MOTD
        String configuredMOTD = ConfigHandler.getBaseConfig().getString("server-MOTD");

        // Null and Empty check to prevent weird behaviour
        if (configuredMOTD == null || configuredMOTD.isEmpty()) {
            event.setMotd(ChatColor.translateAlternateColorCodes('&', "&4No MOTD config key found. Please add 'serverMOTD' to the config file to remove this message."));
            return;
        }

        // Set the new MOTD
        event.setMotd(ChatColor.translateAlternateColorCodes('&', configuredMOTD));
    }
}
