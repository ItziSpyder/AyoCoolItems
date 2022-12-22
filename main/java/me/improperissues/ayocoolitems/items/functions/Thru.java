package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class Thru {

    public static void function(Player player) {
        if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
            Sounds.playAll(player.getLocation(), Sound.ENTITY_ENDER_EYE_DEATH,1,1,500);
            Particle.DustOptions dust = new Particle.DustOptions(Color.PURPLE,5);
            player.getWorld().spawnParticle(Particle.REDSTONE,player.getLocation(),50,1,1,1,1,dust);
            GameMode gm = player.getGameMode();
            player.setGameMode(GameMode.SPECTATOR);
            getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Sounds.playAll(player.getLocation(), Sound.ENTITY_ENDER_EYE_DEATH,1,1,500);
                    Particle.DustOptions dust = new Particle.DustOptions(Color.PURPLE,5);
                    player.getWorld().spawnParticle(Particle.REDSTONE,player.getLocation(),50,1,1,1,1,dust);
                    player.setGameMode(gm);
                }
            },60);
        }
    }
}
