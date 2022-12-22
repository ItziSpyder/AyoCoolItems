package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.entity.CustomTNT;
import me.improperissues.ayocoolitems.events.OnClick;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class FlintAndSteel {

    public static void function(Player player, Block block, Action action) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 500);

        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            CustomTNT.spawnNew(block,player);
            block.setType(Material.AIR);
            player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE,1,1);
            player.playSound(player.getLocation(),Sound.ENTITY_TNT_PRIMED,1,1);
        }
    }
}
