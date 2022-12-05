package me.improperissues.ayocoolitems.other;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

    public static void playAll(Location location, Sound sound, float volume, float pitch, double radius) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != null && player.getWorld() == location.getWorld() && player.getLocation().distanceSquared(location) < radius) {
                player.playSound(location,sound,volume,pitch);
            }
        }
    }

}
