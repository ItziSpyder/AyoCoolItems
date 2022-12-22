package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;

public class Wither {

    public static void function(Player player, ItemStack item) {
        String display = item.getItemMeta().getDisplayName();
        OnClick.deductStack(player);
        Sounds.playAll(player.getLocation(), Sound.ENTITY_WITHER_SHOOT,1,1,500);
        WitherSkull fb = player.getWorld().spawn(player.getEyeLocation(), WitherSkull.class);
        fb.setShooter(player);
        fb.setBounce(false);
        fb.setYield(0);
        fb.setCustomName(display);
        fb.setVelocity(player.getLocation().getDirection());
        if ((int) Math.ceil(Math.random() * 2) == 2) {
            fb.setCharged(true);
        }
    }
}
