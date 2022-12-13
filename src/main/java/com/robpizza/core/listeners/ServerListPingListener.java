package com.robpizza.core.listeners;

import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        String configuredMOTD = ConfigHandler.getBaseConfig().getString("serverMOTD");
        if (configuredMOTD == null) {
            event.setMotd(ChatColor.translateAlternateColorCodes('&', "&4No MOTD config key found. Please add 'serverMOTD' to the config file to remove this message."));
            return;
        }
        event.setMotd(ChatColor.translateAlternateColorCodes('&', configuredMOTD));
    }
}
