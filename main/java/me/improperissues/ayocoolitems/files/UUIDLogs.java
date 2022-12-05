package me.improperissues.ayocoolitems.files;

import me.improperissues.ayocoolitems.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class UUIDLogs {

    private static File file;
    private static FileConfiguration data;

    public static void setup() {
        file = new File("logs/entityuuids/uuidlogs.yml");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException exception) {
            getServer().getLogger().warning("Could not create file " + file.getName() + " !");
        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static void save() {
        try {
            List<String> list = data.getStringList("log.uuids");
            data.set("log.uuids",list);
            data.save(file);
        } catch (IOException exception) {
            getServer().getLogger().warning("Could not save file " + file.getName() + " !");
        }
    }

    public static FileConfiguration get() {
        return data;
    }

    public static void reload() {
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static void addLine(Entity entity) {
        List<String> list = data.getStringList("log.uuids");
        list.add(entity.getUniqueId().toString());
        data.set("log.uuids",list);
        //save();
    }

    public static void removeLine(UUID uuid) {
        List<String> list = data.getStringList("log.uuids");
        list.remove(uuid.toString());
        data.set("log.uuids",list);
        //save();
    }

    public static List<String> getLines() {
        return data.getStringList("log.uuids");
    }

    public static void clearLog(Player player) {
        List<String> uuids = getLines();
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < uuids.size()) {
                    for (int j = 0; j < 20; j ++) {
                        try {
                            String uuid = uuids.get(i);
                            getServer().dispatchCommand(getServer().getConsoleSender(),"kill " + uuid);
                            removeLine(UUID.fromString(uuid));
                            i ++;
                        } catch (IndexOutOfBoundsException exception) {
                            // empty
                        }
                    }
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cClearing " +
                            "log and logged entities: §e" + (uuids.size() - i) + " §cleft! TPS: §e" + Main.tps));
                } else {
                    save();
                    this.cancel();
                }
            }
        }.runTaskTimer(Files.plugin,0,1);
    }
}
