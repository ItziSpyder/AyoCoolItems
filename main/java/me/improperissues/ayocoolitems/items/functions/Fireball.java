package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.improperissues.ayocoolitems.events.OnClick.deductStack;

public class Fireball {

    public static void function(Player player, ItemStack item) {
        String display = item.getItemMeta().getDisplayName();
        deductStack(player);
        Sounds.playAll(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT,1,1,500);
        org.bukkit.entity.Fireball fb = player.getWorld().spawn(player.getEyeLocation(), org.bukkit.entity.Fireball.class);
        fb.setShooter(player);
        fb.setBounce(false);
        fb.setYield(0);
        fb.setCustomName(display);
        fb.setVelocity(player.getLocation().getDirection());
    }
}
