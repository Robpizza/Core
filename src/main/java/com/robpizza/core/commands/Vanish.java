package com.robpizza.core.commands;

import com.robpizza.core.objects.CPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.robpizza.core.plugin.ConfigHandler.__;

public class Vanish extends CoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
        // Set command prefix
        setPrefix("Vanish");

        // Check if the sender is a player
        if (sender instanceof Player) {
            CPlayer cPlayer = new CPlayer((Player) sender);

            // Check if the command is correct
            if (command.getName().equalsIgnoreCase("vanish")) {

                // Check if the player has the correct permissions
                if (!hasPermission(cPlayer, "core.vanish")) {
                    return true;
                }

                // Check command argument length
                if (args.length == 0) {

                    // Check the current state
                    if (cPlayer.isVanish()) {
                        cPlayer.setVanish(false);
                        sendPlayerMessage(cPlayer, __("vanish-disabled"));
                        return true;
                    }

                    cPlayer.setVanish(true);
                    sendPlayerMessage(cPlayer, __("vanish-enabled"));
                    return true;
                }

                // More arguments were provided
                // Check for additional permission
                if(!hasPermission(cPlayer, "core.vanish.other")) {
                    return true;
                }
            }
        }
        return true;
    }
}
