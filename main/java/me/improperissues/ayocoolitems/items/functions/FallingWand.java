package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Displays;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class FallingWand {

    public static void function(Player player) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 1);
        Block block = player.getTargetBlockExact(5);
        block.getWorld().spawnFallingBlock(block.getLocation().add(0.5,0,0.5),block.getType(),(byte) 1);
        Displays.outline(block.getLocation());
        block.setType(Material.AIR);
    }
}
