package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Magnet {

    public static void function(Player player) {
        Sounds.playAll(player.getLocation(), Sound.BLOCK_BELL_RESONATE,1,10,500);
        for (Entity entity : player.getNearbyEntities(20,20,20)) {
            if (entity != player) {
                entity.setVelocity((player.getLocation().toVector().subtract(entity.getLocation().toVector())).normalize());
            }
        }
    }
}
