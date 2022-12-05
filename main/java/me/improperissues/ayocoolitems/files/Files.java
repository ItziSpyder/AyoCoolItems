package me.improperissues.ayocoolitems.files;

import me.improperissues.ayocoolitems.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class Files implements Listener {

    public static Main plugin;
    public Files(Main plugin) {
        Files.plugin = plugin;
    }

    public static FileConfiguration getConfig() {
        return plugin.getConfig();
    }
}
