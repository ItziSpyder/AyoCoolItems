package me.improperissues.ayocoolitems.files;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class Warps {

    // Files
    private static File file;
    private static FileConfiguration data;

    // Setup
    public static void setup() {
        file = new File("plugins/AyoCoolItems/locations/warps.yml");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException exception) {
            getServer().getLogger().warning(exception.toString());
        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static void save() {
        try {
            data.save(file);
        } catch (IOException exception) {
            getServer().getLogger().warning(exception.toString());
        }
    }

    public static FileConfiguration get() {
        return data;
    }

    public static void reload() {
        data = YamlConfiguration.loadConfiguration(file);
    }


    // Utils
    public static List<String> listWarps() {
        ConfigurationSection section = data.getConfigurationSection("warps");
        if (section == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(section.getKeys(false));
    }

    public static Location getWarp(String key) {
        return data.getLocation("warps." + key);
    }

    public static void addWarp(String key, Location location) {
        data.set("warps." + key, location);
        save();
    }

    public static void removeWarp(String key) {
        data.set("warps." + key, null);
        save();
    }
}
