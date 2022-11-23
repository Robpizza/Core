package com.robpizza.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        message = event.getPlayer().hasPermission("core.color.chat")
                ? ChatColor.translateAlternateColorCodes('&', message)
                : message;

        event.setMessage(message);
    }
}
