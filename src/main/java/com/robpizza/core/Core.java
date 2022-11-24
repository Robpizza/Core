package com.robpizza.core;

import com.robpizza.core.listeners.AsyncPlayerChatListener;
import com.robpizza.core.listeners.BlockBreakListener;
import com.robpizza.core.listeners.SignChangeListener;
import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Core extends JavaPlugin {
    @Override
    public void onEnable() {
        // Initialize Core
        initializeCoreHooks();

        // Setup the configurations datafolder
        ConfigHandler.initializeConfigHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initializeCoreHooks() {
        Bukkit.getLogger().log(Level.INFO, "");
        registerEventListeners();
        Bukkit.getLogger().log(Level.INFO, "[CORE] Listeners registered succesful!");
        Bukkit.getLogger().log(Level.INFO, "");
        Bukkit.getLogger().log(Level.INFO, "[CORE] Please leave a rating on the Spigot page!");
        Bukkit.getLogger().log(Level.INFO, "[CORE] https://www.spigotmc.org/resources/core.32613/");
        Bukkit.getLogger().log(Level.INFO, "");
    }

    private void registerEventListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new SignChangeListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
}
