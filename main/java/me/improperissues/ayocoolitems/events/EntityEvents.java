package me.improperissues.ayocoolitems.events;

import me.improperissues.ayocoolitems.files.UUIDLogs;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;

public class EntityEvents implements Listener {

    @EventHandler
    public static void EntitySpawnEvent(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        UUIDLogs.addLine(entity);
    }

    @EventHandler
    public static void EntityDeathEvent(EntityDeathEvent e) {

    }

    @EventHandler
    public static void EntityDamageEvent(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (OnClick.isImmortal(entity)) e.setCancelled(true);
    }

    @EventHandler
    public static void EntityPotionEffectEvent(EntityPotionEffectEvent e) {
        Entity entity = e.getEntity();
        if (OnClick.isImmortal(entity)) e.setCancelled(true);
    }

    @EventHandler
    public static void PotionSplashEvent(PotionSplashEvent e) {
        Entity entity = e.getEntity();
        for (Entity affected : e.getAffectedEntities()) {
            if (OnClick.isImmortal(affected)) e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public static void LingeringPotionSplashEvent(LingeringPotionSplashEvent e) {
        Entity entity = e.getEntity();
        AreaEffectCloud a = e.getAreaEffectCloud();
        for (Entity affected : a.getNearbyEntities(a.getRadius(),0.5,a.getRadius())) {
            if (OnClick.isImmortal(affected)) e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public static void BlockPlaceEvent(BlockPlaceEvent e) {
        Block block = e.getBlock();
        Location loc = block.getLocation();

        if (
                block.getType().equals(Material.PINK_WOOL)
                && loc.clone().add(0,-1,0).getBlock().getType().equals(Material.DIRT)
                && loc.clone().add(0,-2,0).getBlock().getType().equals(Material.DIRT)
                && loc.clone().add(0,-3,0).getBlock().isPassable()
        ) {
            if (loc.clone().add(1,-3,0).getBlock().getType().equals(Material.DIRT) && loc.clone().add(-1,-3,0).getBlock().getType().equals(Material.DIRT)) {
                block.setType(Material.AIR);
                loc.clone().add(0,-1,0).getBlock().setType(Material.AIR);
                loc.clone().add(0,-2,0).getBlock().setType(Material.AIR);
                loc.clone().add(1,-3,0).getBlock().setType(Material.AIR);
                loc.clone().add(-1,-3,0).getBlock().setType(Material.AIR);
                Pig pig = loc.getWorld().spawn(loc.clone().add(0.5,-3,0.5),Pig.class);
                pig.setCustomName("§c§l§o§nPP PIG!!!!");
                pig.setCustomNameVisible(true);
                pig.setGlowing(true);
                return;
            } else if (loc.clone().add(0,-3,1).getBlock().getType().equals(Material.DIRT) && loc.clone().add(0,-3,-1).getBlock().getType().equals(Material.DIRT)) {
                block.setType(Material.AIR);
                loc.clone().add(0,-1,0).getBlock().setType(Material.AIR);
                loc.clone().add(0,-2,0).getBlock().setType(Material.AIR);
                loc.clone().add(0,-3,1).getBlock().setType(Material.AIR);
                loc.clone().add(0,-3,-1).getBlock().setType(Material.AIR);
                Pig pig = loc.getWorld().spawn(loc.clone().add(0.5,-3,0.5),Pig.class);
                pig.setCustomName("§c§l§o§nPP PIG!!!!");
                pig.setCustomNameVisible(true);
                pig.setGlowing(true);
                return;
            }
        }
    }
}
