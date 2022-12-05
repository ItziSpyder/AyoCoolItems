package me.improperissues.ayocoolitems.other;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.files.Files;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Raycast {

    static Main plugin = Files.plugin;

    public static void zap(Player player, Location location1, Location location2, Color color) {
        Vector dir = location2.toVector().subtract(location1.toVector()).normalize();
        for (double distance = 0; distance < 200; distance += 0.06) {
            double x = dir.getX() * distance;
            double y = dir.getY() * distance;
            double z = dir.getZ() * distance;
            Location newLoc = location1.clone().add(x,y,z);
            newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,1,0,0,0,0,new Particle.DustOptions(color,0.2F));
            if (newLoc.distanceSquared(location2) < 0.001) {
                break;
            }
            for (Entity entity : newLoc.getWorld().getNearbyEntities(newLoc,2,2,2)) {
                if (entity != player && entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(2,player);
                }
            }
        }
        Sounds.playAll(location2, Sound.ENTITY_BEE_HURT,1,10,500);
    }

    public static void tazer(Player player) {
        Sounds.playAll(player.getLocation(), Sound.ENTITY_BEE_HURT,1,0.1F,500);
        new BukkitRunnable() {
            Location loc = player.getEyeLocation();
            Vector dir = player.getLocation().getDirection();
            double distance = 0;

            @Override
            public void run() {
                if (distance < 500) {
                    for (int i = 0; i < 6; i ++) {
                        double x = dir.getX() * distance;
                        double y = dir.getY() * distance;
                        double z = dir.getZ() * distance;
                        Location newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z);
                        List<Entity> entities = new ArrayList<>(newLoc.getWorld().getNearbyEntities(newLoc, 5, 5, 5));
                        entities.removeIf(entity -> !(entity instanceof LivingEntity) || entity == player);
                        Particle.DustOptions dust = new Particle.DustOptions(Color.AQUA,0.5F);
                        newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,1,0,0,0,0,dust);
                        dust = new Particle.DustOptions(Color.BLUE,0.5F);
                        newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,1,0,0,0,0,dust);

                        if (entities.size() > 0) {
                            LivingEntity entity = (LivingEntity) entities.get(0);
                            zap(player,newLoc, entity.getEyeLocation(),Color.AQUA);
                            dir = entity.getEyeLocation().toVector().subtract(newLoc.toVector()).normalize();
                        }
                        distance += 0.4;
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin,0,1);
    }

    public static void magneticRevolver(Player player) {
        Sounds.playAll(player.getLocation(), Sound.ENTITY_BEE_HURT,1,0.1F,500);
        new BukkitRunnable() {
            Location loc = player.getEyeLocation();
            Vector dir = player.getLocation().getDirection();
            double distance = 0;
            double vectorAdd = 0;

            @Override
            public void run() {
                if (distance < 20) {
                    for (int i = 0; i < 2; i ++) {
                        double x = dir.getX() * distance;
                        double y = dir.getY() * distance + vectorAdd;
                        double z = dir.getZ() * distance;
                        Location newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z);
                        List<Entity> entities = new ArrayList<>(newLoc.getWorld().getNearbyEntities(newLoc, 5, 5, 5));
                        entities.removeIf(entity -> !(entity instanceof LivingEntity) || entity == player);
                        Particle.DustOptions dust = new Particle.DustOptions(Color.YELLOW,0.5F);
                        newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,1,0,0,0,0,dust);
                        dust = new Particle.DustOptions(Color.ORANGE,0.5F);
                        newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,1,0,0,0,0,dust);

                        for (Entity entity : entities) {
                            Vectors.drag(entity,newLoc);
                        }
                        if (distance > 14 && entities.size() > 0) {
                            vectorAdd += 1.5;
                        }
                        if (!newLoc.getBlock().isPassable()) {
                            distance = 19;
                        }
                        if (distance >= 19 && entities.size() > 0) {
                            for (Entity entity : newLoc.getWorld().getNearbyEntities(newLoc, 10, 10, 10)) {
                                if (entity != player && entity instanceof LivingEntity) {
                                    ((LivingEntity) entity).setHealth(0);
                                }
                            }
                            newLoc.getWorld().spawnParticle(Particle.CLOUD,newLoc,500,0,0,0,0.5);
                            Sounds.playAll(newLoc,Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,10,1,10000);
                            Displays.forceField(player,newLoc);
                        }
                        distance += 0.4;
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin,0,1);
    }
}
