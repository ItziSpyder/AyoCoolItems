package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static org.bukkit.Bukkit.getServer;

public class TpStick {

    public static void function(Player player) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 1000);
        Location loc = player.getEyeLocation();
        Vector dir = player.getLocation().getDirection().normalize();
        // raycast
        for (double distance = 0; distance < 500; distance += 0.2) {
            double x = dir.getX() * distance;
            double y = dir.getY() * distance;
            double z = dir.getZ() * distance;
            Location newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z,loc.getYaw(),loc.getPitch());
            Particle.DustOptions dust = new Particle.DustOptions(Color.RED,1);
            newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,2,0,0,0,0,dust);
            // block hit
            Block block = newLoc.getWorld().getBlockAt(newLoc);
            if (!block.isPassable()) {
                x = dir.getX() * (distance - 2);
                y = dir.getY() * (distance - 2);
                z = dir.getZ() * (distance - 2);
                newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z,loc.getYaw(),loc.getPitch());
                Location finalNewLoc = newLoc;
                getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        player.teleport(finalNewLoc);
                    }
                },5);
                break;
            }
        }
        Sounds.playAll(player.getLocation(), Sound.ENTITY_SHULKER_TELEPORT,1,10,500);
    }
}
