package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Sounds;
import me.improperissues.ayocoolitems.other.Vectors;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class AnimeSword {

    public static List<Entity> tokill = new ArrayList<>();

    public static void function(Player player, Entity entity) {
        if (tokill.contains(entity)) {
            return;
        }

        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 1000);
        List<Entity> entities = new ArrayList<>(player.getNearbyEntities(5,5,5));
        entities.removeIf(en -> !(en instanceof LivingEntity) || en == player);
        tokill.addAll(entities);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < entities.size()) {
                    Entity entity = entities.get(count);
                    Location loc = Vectors.getTargetVector(entity,-2);
                    loc.setY(entity.getLocation().getY());
                    player.teleport(loc);
                    player.getWorld().spawnParticle(Particle.SWEEP_ATTACK,Vectors.getEyeTargetVector(player,1),1,0,0,0,0);
                    player.getWorld().spawnParticle(Particle.REDSTONE,Vectors.getEyeTargetVector(player,1).add(0,-0.5,0),20,0.5,0.5,0.5,0, new Particle.DustOptions(Color.RED,1));
                    Sounds.playAll(player.getLocation(), Sound.BLOCK_GLASS_BREAK,2,10,200);
                    Sounds.playAll(player.getLocation(),Sound.ENTITY_PLAYER_ATTACK_SWEEP,1,0.1F,200);
                    Sounds.playAll(player.getLocation(),Sound.ITEM_TRIDENT_THROW,1,1F,200);
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,255,false));
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,999999,1,false));
                    count ++;
                } else {
                    getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            for (Entity entity : entities) {
                                ((LivingEntity) entity).damage(1,player);
                                ((LivingEntity) entity).setHealth(0);
                            }
                        }
                    },20);
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(),0,1);
    }
}
