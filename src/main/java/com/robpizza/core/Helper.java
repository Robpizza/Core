package com.robpizza.core;

import com.robpizza.core.objects.CPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Helper {
    public static void sendColorMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendColorMessage(CPlayer cPlayer, String message) {
        sendColorMessage(cPlayer.getPlayer(), message);
    }
}
