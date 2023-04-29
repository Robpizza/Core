package com.robpizza.core.commands;

import com.robpizza.core.objects.CPlayer;
import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import static com.robpizza.core.Helper.sendColorMessage;
import static com.robpizza.core.plugin.ConfigHandler.__;

public abstract class CoreCommand {

    protected String prefix = "&6&lCore";

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    public boolean hasPermission(final @NotNull CPlayer cPlayer, final String permission) {

        if(!cPlayer.getPlayer().hasPermission(permission)) {
            sendPlayerMessage(cPlayer, __("no-permissions"));
            return false;
        }

        return true;
    }

    public void sendPlayerMessage(final CPlayer cPlayer, final String message) {
        if(ConfigHandler.getBaseConfig().getBoolean("feedback-prefix")) {
            sendColorMessage(cPlayer, this.prefix + "&r | " + message);
        } else {
            sendColorMessage(cPlayer, message);
        }
    }

    public void sendPublicMessage(final String message) {
        if(ConfigHandler.getBaseConfig().getBoolean("feedback-prefix")) {
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&r | " + message));
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
