package me.improperissues.ayocoolitems.other;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerUtils {

    public static List<String> getPlayers() {
        List<String> players = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            players.add(p.getName());
        }
        return players;
    }

    public static List<String> getOped() {
        List<String> players = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isOp()) players.add(p.getName());
        }
        return players;
    }

    public static void killallTags(String scoreboardTag) {
        for (World world : Bukkit.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getScoreboardTags().contains(scoreboardTag)) entity.remove();
            }
        }
    }
}
