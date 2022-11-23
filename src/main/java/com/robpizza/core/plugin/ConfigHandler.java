package com.robpizza.core.plugin;

import com.robpizza.core.Core;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigHandler {
    private static Core pluginInstance;
    private static FileConfiguration configuration;
    private static File configFile;



    public static void initializeConfigHandler(final Core coreInstance) {
        pluginInstance = coreInstance;
        configFile = new File(coreInstance.getDataFolder(), "config.yml");

        if(!configFile.exists()) {
            coreInstance.saveDefaultConfig();
        }

        reloadConfigs();
    }

    private static void reloadConfigs() {
        configuration = new YamlConfiguration();
        try {
            configuration.load(configFile);
        } catch (IOException | InvalidConfigurationException exception) {
            pluginInstance.getLogger().log(Level.SEVERE, "Could not load config file!", exception);
        }
    }

    public FileConfiguration getConfig() {
        // Refresh configs with newest values
        reloadConfigs();

        // Return configuration
        return configuration;
    }

}
