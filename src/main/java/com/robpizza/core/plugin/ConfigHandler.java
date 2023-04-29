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
    private static FileConfiguration configuration, language;
    private static File configFile, languageFile;

    /**
     * Initializes the configuration handler
     *
     */
    public static void initializeConfigHandler() {
        configFile = new File(Core.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(Core.getInstance().getDataFolder(), "language.yml");

        if (!configFile.exists()) {
            Core.getInstance().saveDefaultConfig();
        }

        if(!languageFile.exists()) {
            languageFile.getParentFile().mkdirs();
            Core.getInstance().saveResource("language.yml", false);
        }

        reloadConfigs();
    }

    /**
     * Method to 'refresh' the in memory saved configuration
     * Should be called everytime a config file is requested.
     */
    private static void reloadConfigs() {
        configuration = new YamlConfiguration();
        language = new YamlConfiguration();
        try {
            configuration.load(configFile);
            language.load(languageFile);
        } catch (IOException | InvalidConfigurationException exception) {
            Core.getInstance().getLogger().log(Level.SEVERE, "Could not load config file!", exception);
        }
    }

    /**
     * Returns the 'config.yml' configuration
     *
     * @return FileConfiguration type of 'config.yml'
     */
    public static FileConfiguration getBaseConfig() {
        // Refresh configs with newest values
        reloadConfigs();

        // Return configuration
        return configuration;
    }

    public static String __(String name) {
        reloadConfigs();

        return language.getString(name);
    }

}
