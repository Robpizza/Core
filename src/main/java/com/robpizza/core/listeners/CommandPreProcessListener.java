package com.robpizza.core.listeners;

import com.robpizza.core.objects.CPlayer;
import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

import static com.robpizza.core.Helper.sendColorMessage;
import static com.robpizza.core.plugin.ConfigHandler.__;

public class CommandPreProcessListener implements Listener {
    @EventHandler
    public void onVanishedCommand(PlayerCommandPreprocessEvent event) {
        CPlayer cPlayer = new CPlayer(event.getPlayer());

        // Check if player is vanished
        if (cPlayer.isVanish()) {

            // Get the list with blocked commands
            List<String> allowedCommands = ConfigHandler.getBaseConfig().getStringList("vanish.allowed-commands");

            // Parse command from player input
            String command = event.getMessage();
            command = command.replace("/", "");

            // Check if the parsed command is a vanish related one.
            // They get a default exception
            if(command.equalsIgnoreCase("vanish") || command.equalsIgnoreCase("v")) {
                return;
            }


            // Get the requested command
            if(!allowedCommands.contains(command)) {
                event.setCancelled(true);
                sendColorMessage(cPlayer, __("vanished-not-allowed-command"));
            }
        }
    }
}
