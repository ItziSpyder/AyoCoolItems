package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Messages;
import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class AshWand {

    public static void function(Player player) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 2000);
        Sounds.playAll(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH,10,0.1F,500);
        Location loc = player.getEyeLocation();
        Vector dir = player.getLocation().getDirection().normalize();
        // raycast
        List<String> names = new ArrayList<>();
        for (double distance = 0; distance < 200; distance += 0.5) {
            OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 2000);
            double x = dir.getX() * distance;
            double y = dir.getY() * distance;
            double z = dir.getZ() * distance;
            Location newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z);
            Particle.DustOptions dust = new Particle.DustOptions(Color.BLACK,5);
            newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,2,0,0,0,0,dust);
            // entity hit
            for (Entity entity : newLoc.getWorld().getNearbyEntities(newLoc,2,2,2)) {
                OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 2000);
                if (entity != player && entity instanceof LivingEntity) {
                    LivingEntity le = (LivingEntity) entity;
                    le.damage(5,player);
                    Sounds.playAll(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT,10,0.1F,500);
                    newLoc.getWorld().spawnParticle(Particle.FLAME,newLoc,999,2,2,2,0.1);
                    // entity combust
                    for (Entity nearby : entity.getWorld().getNearbyEntities(entity.getLocation(),5,5,5)) {
                        if (nearby != player && nearby instanceof LivingEntity) {
                            Particle.DustOptions dust2 = new Particle.DustOptions(Color.GRAY,5);
                            nearby.getWorld().spawnParticle(Particle.REDSTONE,nearby.getLocation(),50,1,1,1,1,dust2);
                            names.add(nearby.getName());
                            if (nearby instanceof Player) {
                                ((Player) nearby).setHealth(0F);
                            } else {
                                nearby.remove();
                            }
                        }
                    }
                    distance = 200;
                    break;
                }
            }
        }
        String name = names.toString().substring(1,names.toString().length() - 1);
        if (names.size() > 0) {
            Messages.bm("§7" + name + " §fturned to ashes by a beam shot from §7" + player.getName() + "'s §fash wand");
        }
    }
}
