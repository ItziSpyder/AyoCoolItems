package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Displays;
import me.improperissues.ayocoolitems.other.Messages;
import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getServer;

public class Taker {

    public static void function(Player player, Entity entity) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 2000);
        // living entity filter
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        // add the potion effects
        LivingEntity le = (LivingEntity) entity;
        le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,255,false));
        le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,999999,255,false));
        le.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,999999,360,false));
        Displays.wave(le.getLocation(),2,0.3, Color.BLACK,3);
        // sink the ship
        getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                new BukkitRunnable() {
                    double waves = 0;
                    @Override
                    public void run() {
                        if (waves < 16) {
                            le.teleport(le.getLocation().add(0,-0.07,0));
                            waves += 0.3;
                        } else {
                            le.teleport(le.getLocation().add(0,-999999,0));
                            le.setHealth(0);
                            Messages.bm("§c" + le.getName() + " §4was taken");
                            this.cancel();
                            return;
                        }
                    }
                }.runTaskTimer(Main.getInstance(),0,1);
            }
        },20);
    }
}
