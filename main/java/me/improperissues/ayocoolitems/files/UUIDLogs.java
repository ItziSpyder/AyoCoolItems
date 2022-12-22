package me.improperissues.ayocoolitems.files;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.other.Messages;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
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
                    for (int j = 0; j < 100; j ++) {
                        try {
                            String uuid = uuids.get(i);
                            Entity entity = Bukkit.getEntity(UUID.fromString(uuid));
                            if (entity != null && entity.isDead()) {
                                getServer().dispatchCommand(getServer().getConsoleSender(),"kill " + entity.getUniqueId());
                            }
                            removeLine(UUID.fromString(uuid));
                            i ++;
                        } catch (IndexOutOfBoundsException exception) {
                            break;
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

    // Commands
    public static void getallUUID(Player player) {
        player.sendMessage(Messages.starter + "cGetting logged entities, this may take up to minutes!");
        new BukkitRunnable() {
            int dead = 0;
            List<String> entities = new ArrayList<>();
            int i = 0;
            long timestarted = System.currentTimeMillis();
            @Override
            public void run() {
                if (i < UUIDLogs.getLines().size()) {
                    for (int j = 0; j < 100; j ++) {
                        try {
                            String uuid = UUIDLogs.getLines().get(i);
                            try {
                                Entity entity = Bukkit.getEntity(UUID.fromString(uuid));
                                if (!entity.isDead()) entities.add(entity.getType().name().toLowerCase()); else dead ++;
                            } catch (NullPointerException exception) {
                                dead ++;
                            }
                            i ++;
                        } catch (IndexOutOfBoundsException exception) {
                            break;
                        }
                    }
                } else {
                    double timefinished = Math.ceil((System.currentTimeMillis() - timestarted) / 10) / 100;
                    player.sendMessage(Messages.starter + "cCurrent entities are: " +
                            "§7" + entities.toString() + "\n" + Messages.starter + "cThere are §e" +
                            entities.size() + " §centities and §e" + dead + " §cdead entities! Process finished in §e" + timefinished + " §cseconds!");
                    this.cancel();
                }
            }
        }.runTaskTimer(Files.plugin,0,1);
    }
}
