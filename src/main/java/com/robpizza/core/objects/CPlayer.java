package com.robpizza.core.objects;

import com.robpizza.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CPlayer {
    private final Player player;

    public CPlayer(final UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }

    public CPlayer(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    public boolean isVanish() {
        return Core.getVanishedPlayers().containsKey(getUniqueId());
    }

    public void setVanish(final boolean enableVanish) {
        VanishedPlayerMemory playerMemory;
        if (enableVanish) {
            // Create a new Memory object of this player
            playerMemory = new VanishedPlayerMemory(getPlayer().getLocation(), getPlayer().getInventory().getContents(), getPlayer().getInventory().getArmorContents(), getPlayer().getGameMode());

            // Store the memory in a global Map
            Core.getVanishedPlayers().put(getUniqueId(), playerMemory);

            // Clear out the player inventory and equipment
            getPlayer().getInventory().clear();
            if (getPlayer().getEquipment() != null) {
                getPlayer().getEquipment().clear();
            }

            // Enable flight for this player
            getPlayer().setAllowFlight(true);
            getPlayer().setFlying(true);

            // Hide this player for all other players
            // TODO Add an additional permission to bypass the hide for specific people?
            //  For example server staff
            for (Player playerX : Bukkit.getServer().getOnlinePlayers()) {
                playerX.hidePlayer(Core.getInstance(), getPlayer());
            }
        } else {

            // Retrieve the PlayerMemory
            playerMemory = Core.getVanishedPlayers().get(getUniqueId());

            // Disable fly for this player if player was in Survival/Adventure Mode
            if(playerMemory.getGameMode() == GameMode.SURVIVAL || playerMemory.getGameMode() == GameMode.ADVENTURE) {
                getPlayer().setFlying(false);
                getPlayer().setAllowFlight(false);
            }

            // Restore the players inventory and equipment
            getPlayer().getInventory().setArmorContents(playerMemory.getArmor());
            getPlayer().getInventory().setContents(playerMemory.getItems());

            // Teleport the player back to their initial location
            getPlayer().teleport(playerMemory.getLocation());

            // Remove the player from memory
            Core.getVanishedPlayers().remove(getUniqueId());

            // Show the player
            for (Player playerX : Bukkit.getServer().getOnlinePlayers()) {
                playerX.showPlayer(Core.getInstance(), getPlayer());
            }
        }
    }
}
