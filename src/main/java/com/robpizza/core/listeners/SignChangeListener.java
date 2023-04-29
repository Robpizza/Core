package com.robpizza.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {
    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event) {
        // Loop over all lines
        for (int line = 0; line < 4; line++) {

            // Get the line text
            String lineText = event.getLine(line);

            // Null and Empty check
            if (lineText != null && !lineText.isEmpty()) {

                // Check for color permissions and apply colors if required
                lineText = event.getPlayer().hasPermission("core.color.sign")
                        ? ChatColor.translateAlternateColorCodes('&', lineText)
                        : lineText;

                // Set the line text
                event.setLine(line, lineText);
            }
        }
    }
}
