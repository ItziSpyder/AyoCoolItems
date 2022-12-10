package me.improperissues.ayocoolitems.other;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.files.Files;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class Displays {
    static Main plugin = Files.plugin;

    public static void ring(Location location, Particle particle, double radius) {
        for (double circle = 0; circle < 120; circle += 0.5) {
            double x = Math.cos(circle) * radius;
            double z = Math.sin(circle) * radius;
            Location newLoc = new Location(location.getWorld(),location.getX() + x,location.getY(),location.getZ() + z);
            newLoc.getWorld().spawnParticle(particle,newLoc,1,0,0,0,0);
        }
    }

    public static void ring(Location location, double radius, Color color, float size) {
        for (double circle = 0; circle < 120; circle += 0.5) {
            double x = Math.cos(circle) * radius;
            double z = Math.sin(circle) * radius;
            Location newLoc = new Location(location.getWorld(),location.getX() + x,location.getY(),location.getZ() + z);
            Particle.DustOptions dust = new Particle.DustOptions(color,size);
            newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,1,0,0,0,0,dust);        }
    }

    public static void wave(Location location, double radius, double gap, Color color, float size) {
        new BukkitRunnable() {
            double waves = 0;
            @Override
            public void run() {
                if (waves < radius) {
                    ring(location,waves,color,size);
                    waves += gap;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin,0,1);
    }

    public static void wave(Location location, Particle particle, double radius, double gap) {
        new BukkitRunnable() {
            double waves = 0;
            @Override
            public void run() {
                if (waves < radius) {
                    ring(location,particle,waves);
                    waves += gap;
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin,0,1);
    }

    public static void storm(Location location) {
        Sounds.playAll(location, Sound.ENTITY_WITHER_AMBIENT,10,0.1F,500);
        Displays.wave(location,Particle.WAX_OFF,10,1);
        getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                new BukkitRunnable() {
                    int times = 0;
                    @Override
                    public void run() {
                        if (times < 10) {
                            Location newLoc = new Location(location.getWorld(),location.getX() + (randNeg() * randMax(10)),location.getY(),location.getZ() + (randNeg() * randMax(10)));
                            newLoc.getWorld().spawn(newLoc, LightningStrike.class);
                            wave(newLoc,Particle.SOUL_FIRE_FLAME,2,0.5);
                            Displays.wave(location,Particle.WAX_OFF,10,1);
                            Sounds.playAll(newLoc, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE,10,0.1F,500);
                            times ++;
                        } else {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin,0,6);
            }
        },20);
    }

    public static void draw(Location location1, Location location2, Color color) {
        Vector dir = location2.toVector().subtract(location1.toVector()).normalize();
        if (location1.getWorld() == location2.getWorld()) {
            for (double distance = 0; distance < 400; distance += 0.1) {
                double x = dir.getX() * distance;
                double y = dir.getY() * distance;
                double z = dir.getZ() * distance;
                Location newLoc = new Location(location1.getWorld(),location1.getX() + x,location1.getY() + y,location1.getZ() + z);
                Particle.DustOptions dust = new Particle.DustOptions(color,0.3F);
                newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,3,0,0,0,0,dust);
                if (newLoc.distanceSquared(location2) < 0.001) {
                    break;
                }
            }
        }
    }

    public static void outline(Location location) {
        Block block = location.getWorld().getBlockAt(location);
        Location pivot = block.getLocation();
        Color color = Color.RED;
        if (block.isPassable()) {
            color = Color.LIME;
        }
        // bottom square
        draw(pivot.clone().add(0,0,0),pivot.clone().add(1,0,0),color);
        draw(pivot.clone().add(0,0,0),pivot.clone().add(0,0,1),color);
        draw(pivot.clone().add(1,0,0),pivot.clone().add(1,0,1),color);
        draw(pivot.clone().add(0,0,1),pivot.clone().add(1,0,1),color);
        // top square
        draw(pivot.clone().add(0,1,0),pivot.clone().add(1,1,0),color);
        draw(pivot.clone().add(0,1,0),pivot.clone().add(0,1,1),color);
        draw(pivot.clone().add(1,1,0),pivot.clone().add(1,1,1),color);
        draw(pivot.clone().add(0,1,1),pivot.clone().add(1,1,1),color);
        // pillars
        draw(pivot.clone().add(0,0,0),pivot.clone().add(0,1,0),color);
        draw(pivot.clone().add(1,0,0),pivot.clone().add(1,1,0),color);
        draw(pivot.clone().add(0,0,1),pivot.clone().add(0,1,1),color);
        draw(pivot.clone().add(1,0,1),pivot.clone().add(1,1,1),color);
        // center point
        Location center = pivot.clone().add(0.5,0.5,0.5);
        draw(center.clone().add(-0.2,0,0),center.clone().add(0.2,0,0),Color.BLUE);
        draw(center.clone().add(0,0,-0.2),center.clone().add(0,0,0.2),Color.BLUE);
        draw(center.clone().add(0,-0.2,0),center.clone().add(0,0.2,0),Color.BLUE);
    }

    public static void forceField(Player player, Location location) {
        Color[] colors = {Color.AQUA,Color.fromRGB(207, 232, 250)};
        double gap = 1;
        Location pivot = location.clone();
        List<String> values = new ArrayList<>(Arrays.asList(
                "4,1",
                "3,3",
                "2,4",
                "1,5",
                "0,5",
                "-1,5",
                "-2,4",
                "-3,3",
                "-4,1"
        ));
        // ring down
        kb(player);
        new BukkitRunnable() {
            int waves = 0;
            @Override
            public void run() {
                if (waves < 9) {
                    String[] info = values.get(waves).split(",");
                    int y = Integer.parseInt(info[0]);
                    int radius = Integer.parseInt(info[1]);
                    ring(pivot.clone().add(0, gap * y, 0), radius, colors[0], 1);
                    waves ++;
                } else {
                    Collections.reverse(values);
                    // ring up
                    kb(player);
                    new BukkitRunnable() {
                        int waves = 0;
                        @Override
                        public void run() {
                            if (waves < 9) {
                                String[] info = values.get(waves).split(",");
                                int y = Integer.parseInt(info[0]);
                                int radius = Integer.parseInt(info[1]);
                                ring(pivot.clone().add(0, gap * y, 0), radius, colors[1], 1);
                                waves ++;
                            } else {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(plugin, 0, 1);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public static void kb(Player center) {
        Location location = center.getLocation();
        Sounds.playAll(location,Sound.ENTITY_ELDER_GUARDIAN_HURT,10,0.1F,1000);
        Sounds.playAll(location,Sound.ENTITY_ELDER_GUARDIAN_HURT,10,0.1F,1000);
        Sounds.playAll(location,Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK,10,1F,1000);
        for (Entity entity : location.getWorld().getNearbyEntities(location,10,10,10)) {
            if (entity != center) {
                entity.setVelocity(entity.getLocation().toVector().subtract(location.toVector()).normalize().multiply(3).add(new Vector(0,2,0)));
                if (entity instanceof LivingEntity) {
                    LivingEntity le = (LivingEntity) entity;
                    ring(le.getEyeLocation(),1,Color.RED,1);
                    le.damage(5,center);
                }
            }
        }
    }

    public static void helix(Location location, double radius, Color color, float size) {
        for (double y = 0; y < 100; y += 0.1) {
            double x = Math.cos(y) * radius;
            double z = Math.sin(y) * radius;
            Location newLoc = location.clone().add(x,y / 6,z);
            Particle.DustOptions dust = new Particle.DustOptions(color,size);
            newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,1,0,0,0,0,dust);
        }
    }

    public static int randNeg() {
        if ((int) Math.ceil(Math.random() * 2) == 2) {
            return -1;
        } else {
            return 1;
        }
    }

    public static int randMax(int max) {
        return (int) Math.ceil(Math.random() * max);
    }
}
