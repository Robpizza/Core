package com.robpizza.core.plugin;

import com.robpizza.core.Core;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Config Handler class of Core
 * Used to manage all the different configuration files used by Core
 */
public class ConfigHandler {
    private static Core pluginInstance;
    private static FileConfiguration configuration;
    private static File configFile;

    /**
     * Initializes the configuration handler
     * @param coreInstance plugin instance
     */
    public static void initializeConfigHandler(final Core coreInstance) {
        pluginInstance = coreInstance;
        configFile = new File(coreInstance.getDataFolder(), "config.yml");

        if(!configFile.exists()) {
            coreInstance.saveDefaultConfig();
        }

        reloadConfigs();
    }

    /**
     * Method to 'refresh' the in memory saved configuration
     * Should be called everytime a config file is requested.
     */
    private static void reloadConfigs() {
        configuration = new YamlConfiguration();
        try {
            configuration.load(configFile);
        } catch (IOException | InvalidConfigurationException exception) {
            pluginInstance.getLogger().log(Level.SEVERE, "Could not load config file!", exception);
        }
    }

    /**
     * Returns the 'config.yml' configuration
     * @return FileConfiguration type of 'config.yml'
     */
    public static FileConfiguration getBaseConfig() {
        // Refresh configs with newest values
        reloadConfigs();

        // Return configuration
        return configuration;
    }

}
