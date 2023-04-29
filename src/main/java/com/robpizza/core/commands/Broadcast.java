package com.robpizza.core.commands;

import com.robpizza.core.objects.CPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.robpizza.core.plugin.ConfigHandler.__;

public class Broadcast extends CoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Set command prefix
        setPrefix("&6&lBroadcast");

        // Check if the sender is a player
        if (sender instanceof Player) {
            CPlayer cPlayer = new CPlayer((Player) sender);

            // Check if the command is correct
            if (command.getName().equalsIgnoreCase("broadcast")) {

                // Check if the player has the correct permissions
                if (!hasPermission(cPlayer, "core.broadcast")) {
                    return true;
                }

                // Check the argument length
                if (args.length == 0) {
                    sendPlayerMessage(cPlayer, __("broadcast-not-enough-arguments"));
                    return true;
                }

//                    // Build a string from the arguments given
                StringBuilder stringBuilder = new StringBuilder(args[0]);
                for (int i = 1; i < args.length; i++) {
                    stringBuilder.append(" ").append(args[i]);
                }
                String message = stringBuilder.toString();
                sendPublicMessage(message);
                return true;
            }
            return true;
        } else {
            // TODO Implement Console use
            Bukkit.getLogger().info("Right now you can only broadcast from in-game.");
        }
        return true;
    }
}
