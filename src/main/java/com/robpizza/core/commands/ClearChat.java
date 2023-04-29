package com.robpizza.core.commands;

import com.robpizza.core.objects.CPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.robpizza.core.plugin.ConfigHandler.__;

public class ClearChat extends CoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
        // Set command prefix
        setPrefix("&6&lClearChat");

        // Check if the sender is a player
        if (sender instanceof Player) {
            CPlayer cPlayer = new CPlayer((Player) sender);

            // Check if the command is correct
            if (command.getName().equalsIgnoreCase("clearchat")) {

                // Check if the player has the correct permissions
                if (!hasPermission(cPlayer, "core.clearchat")) {
                    return true;
                }

                // Send a message to all players
                // TODO Look into this solution
                //  Or sending the message private to each player.
                for (int i = 0; i < 115; i++) {
                    Bukkit.getServer().broadcastMessage("");
                }

                // Send the announcement
                sendPublicMessage(__("chat-cleared", cPlayer.getPlayer().getDisplayName()));
                Bukkit.getServer().broadcastMessage("");
            }
        }
        return true;
    }
}
