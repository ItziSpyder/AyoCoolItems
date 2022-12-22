package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Immortality {

    public static List<UUID> immortal = new ArrayList<>();

    public static void function(Player player) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 1000);
        if (isImmortal(player)) {
            immortal.remove(player.getUniqueId());
            player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE,10,0.1F);
            player.sendTitle("","§f§l§oYou are no longer immortal!",10,40,10);
        } else {
            immortal.add(player.getUniqueId());
            player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE,10,0.1F);
            player.sendTitle("","§6§l§oYou have been granted immortality!",10,40,10);
        }
    }

    public static boolean isImmortal(Entity entity) {
        return immortal.contains(entity.getUniqueId());
    }
}
