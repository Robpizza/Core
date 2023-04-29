package com.robpizza.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        // Get the initial message
        String message = event.getMessage();

        // Check for color permissions and apply colors if required
        message = event.getPlayer().hasPermission("core.color.chat")
                ? ChatColor.translateAlternateColorCodes('&', message)
                : message;

        // Set the newly created message
        event.setMessage(message);
    }
}
