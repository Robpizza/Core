package com.robpizza.core;

import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Setup the configurations datafolder
        ConfigHandler.initializeConfigHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
