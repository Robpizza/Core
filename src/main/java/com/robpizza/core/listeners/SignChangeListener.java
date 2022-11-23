package com.robpizza.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {
    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event) {
        for (int line = 0; line < 4; line++) {
            String lineText = event.getLine(line);
            if (lineText != null && !lineText.isEmpty()) {
                lineText = event.getPlayer().hasPermission("core.color.sign")
                        ? ChatColor.translateAlternateColorCodes('&', lineText)
                        : lineText;

                event.setLine(line, lineText);
            }
        }
    }
}
