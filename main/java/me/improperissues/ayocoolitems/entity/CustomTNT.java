package me.improperissues.ayocoolitems.entity;

import me.improperissues.ayocoolitems.events.EntityEvents;
import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.files.Files;
import me.improperissues.ayocoolitems.items.functions.Immortality;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;

public class CustomTNT {

    public static void spawnNew(Block block, Player player) {
        Material type = block.getType();
        Location loc = block.getLocation().add(0.5,0,0.5);

        Item item = loc.getWorld().spawn(loc,Item.class);
        Immortality.immortal.add(item.getUniqueId());
        item.addScoreboardTag("§8ayocoolitems:cannot_merge");
        item.setPickupDelay(999999999);
        item.setItemStack(new ItemStack(Material.OAK_BUTTON,1));
        item.setSilent(true);
        item.setInvulnerable(true);

        FallingBlock fall = item.getWorld().spawnFallingBlock(item.getLocation().clone().add(0,0.5,0),type, (byte) 1);
        Immortality.immortal.add(fall.getUniqueId());
        fall.setGravity(false);
        fall.setInvulnerable(true);

        item.addPassenger(fall);
        getServer().getScheduler().scheduleSyncDelayedTask(Files.plugin, new Runnable() {
            @Override
            public void run() {
                if (!item.isDead() && !fall.isDead()) loc.getWorld().createExplosion(loc,3,false,true,player);
                if (!item.isDead()) item.remove();
                if (!fall.isDead()) fall.remove();
            }
        },60);
    }
}
