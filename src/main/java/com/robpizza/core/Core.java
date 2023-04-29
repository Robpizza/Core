package com.robpizza.core;

import com.robpizza.core.commands.Vanish;
import com.robpizza.core.listeners.*;
import com.robpizza.core.objects.VanishedPlayerMemory;
import com.robpizza.core.plugin.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public final class Core extends JavaPlugin {

    public static Plugin instance;
    public static Plugin getInstance() {
        return instance;
    }

    private static Map<UUID, VanishedPlayerMemory> vanishedPlayers;
    public static Map<UUID, VanishedPlayerMemory> getVanishedPlayers() {
        return vanishedPlayers;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Setup the configurations data folder
        ConfigHandler.initializeConfigHandler();

        // Initialize Core
        initializeCoreHooks();

        // Initialize a new HashSet
        vanishedPlayers = new HashMap<>();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        vanishedPlayers = null;
    }

    private void initializeCoreHooks() {
        Bukkit.getLogger().log(Level.INFO, "");
        registerEventListeners();
        Bukkit.getLogger().log(Level.INFO, "[CORE] Listeners registered successful!");
        Bukkit.getLogger().log(Level.INFO, "");
        registerCommands();
        Bukkit.getLogger().log(Level.INFO, "[CORE] Core Commands registered successful!");
        Bukkit.getLogger().log(Level.INFO, "");
        Bukkit.getLogger().log(Level.INFO, "[CORE] Please leave a rating on the Spigot page!");
        Bukkit.getLogger().log(Level.INFO, "[CORE] https://www.spigotmc.org/resources/core.32613/");
        Bukkit.getLogger().log(Level.INFO, "");
    }

    private void registerEventListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this); // TODO Not working?
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SignChangeListener(), this);
    }

    private void registerCommands() {
        this.getCommand("vanish").setExecutor(new Vanish());
    }
}
