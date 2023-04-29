package com.robpizza.core.objects;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class VanishedPlayerMemory {
    private final Location location;
    private final ItemStack[] items;
    private final ItemStack[] armor;
    private final GameMode gameMode;

    public VanishedPlayerMemory(final Location location, final ItemStack[] items, final ItemStack[] armor, final GameMode gameMode){
        this.location = location;
        this.items = items;
        this.armor = armor;
        this.gameMode = gameMode;
    }


    public Location getLocation() {
        return location;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
