package me.improperissues.ayocoolitems;

import me.improperissues.ayocoolitems.commands.Commands;
import me.improperissues.ayocoolitems.commands.Tabs;
import me.improperissues.ayocoolitems.events.ChatEvent;
import me.improperissues.ayocoolitems.events.EntityEvents;
import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.events.ServerEvents;
import me.improperissues.ayocoolitems.files.Files;
import me.improperissues.ayocoolitems.files.UUIDLogs;
import me.improperissues.ayocoolitems.files.Warps;
import me.improperissues.ayocoolitems.items.CustomItems;
import me.improperissues.ayocoolitems.other.PlayerTitles;
import me.improperissues.ayocoolitems.other.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static double tps;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getLogger().info("Cool items loaded!");

        // Events
        getServer().getPluginManager().registerEvents(new Files(this),this);
        getServer().getPluginManager().registerEvents(new OnClick(),this);
        getServer().getPluginManager().registerEvents(new EntityEvents(),this);
        getServer().getPluginManager().registerEvents(new ServerEvents(),this);
        getServer().getPluginManager().registerEvents(new ChatEvent(),this);

        // Files
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        UUIDLogs.setup();
        UUIDLogs.get().options().copyDefaults(true);
        UUIDLogs.save();
        Warps.setup();
        Warps.get().options().copyDefaults(true);
        Warps.save();

        // Items
        CustomItems.registerItems();

        // Commands
        getCommand("giveitem").setExecutor(new Commands());
        getCommand("giveitem").setTabCompleter(new Tabs());
        getCommand("toggleoutline").setExecutor(new Commands());
        getCommand("togglevectorhighlight").setExecutor(new Commands());
        getCommand("killall-uuid").setExecutor(new Commands());
        getCommand("getall-uuid").setExecutor(new Commands());
        getCommand("velocity").setExecutor(new Commands());
        getCommand("velocity").setTabCompleter(new Tabs());
        getCommand("reaction").setExecutor(new Commands());
        getCommand("delwarp").setExecutor(new Commands());
        getCommand("delwarp").setTabCompleter(new Tabs());
        getCommand("addwarp").setExecutor(new Commands());
        getCommand("addwarp").setTabCompleter(new Tabs());
        getCommand("warp").setExecutor(new Commands());
        getCommand("warp").setTabCompleter(new Tabs());
        getCommand("rgb-display").setExecutor(new Commands());
        getCommand("spawn").setExecutor(new Commands());

        // Loops
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            long sec;
            long currentsec;
            int ticks;
            @Override
            public void run() {
                OnClick.registerEvents();
                sec = System.currentTimeMillis()/1000;
                if (currentsec == sec) {
                    PlayerTitles.rainbowRGB();
                    ticks ++;
                } else {
                    currentsec = sec;
                    tps = (tps == 0 ? ticks : ((tps + ticks)/2)) + 1;
                    tps = Math.floor(tps * 100) / 100;
                    if (tps > 20.00) {
                        tps = 20.00;
                    }
                    ticks = 0;
                }
            }
        },0,1);
    }

    @Override
    public void onDisable() {
        // Entities
        ServerUtils.killallTags("§cTNT_CRYSTAL");
        // Files
        UUIDLogs.save();
        // Plugin shutdown logic
        getServer().getLogger().info("Cool items disabled!");
    }

    public static Plugin getInstance() {
        return Bukkit.getServer().getPluginManager().getPlugin("AyoCoolItems");
    }
}
