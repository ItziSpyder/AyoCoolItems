package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Messages;
import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class Melonizer {

    public static List<UUID> melonized = new ArrayList<>();

    public static void function(Player player, Entity entity) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 2000);
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        if (isMelonized(entity)) {
            return;
        }
        LivingEntity le = (LivingEntity) entity;
        OnClick.deductStack(player);
        Sounds.playAll(le.getLocation(), Sound.BLOCK_COMPOSTER_EMPTY,1,1,500);
        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(212,70,56),5);
        le.getWorld().spawnParticle(Particle.REDSTONE,le.getLocation(),50,1,1,1,1,dust);
        UUID id = le.getUniqueId();
        melonized.add(id);
        Block block = le.getWorld().getBlockAt(le.getLocation());
        Material type = block.getType();
        if (block.isPassable()) {
            block.setType(Material.MELON);
        }
        GameMode gm = null;
        if (le instanceof Player) {
            Player target = (Player) le;
            gm = target.getGameMode();
            target.setGameMode(GameMode.SPECTATOR);
        } else {
            le.remove();
            le.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,999999,1,false));
            le.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,999999,255,false));
        }
        Messages.bm("§a" + le.getName() + " §2was melonized and frozen");
        GameMode finalGm = gm;
        getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Sounds.playAll(player.getLocation(), Sound.BLOCK_COMPOSTER_EMPTY,1,1,500);
                Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(212,70,56),5);
                le.getWorld().spawnParticle(Particle.REDSTONE,le.getLocation(),50,1,1,1,1,dust);
                if (block.getType().equals(Material.MELON)) {
                    block.setType(type);
                }

                if (le instanceof Player) {
                    Player target = (Player) le;
                    target.setGameMode(finalGm);
                    target.setHealth(0);
                } else {
                    Messages.bm("§a" + le.getName() + " §2was squished and died");
                }
                melonized.remove(id);
            }
        },60);
    }

    public static boolean isMelonized(Entity entity) {
        return melonized.contains(entity.getUniqueId());
    }
}
