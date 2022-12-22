package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.entity.CustomArmorStands;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class TNTCrystal {

    public static void function(Player player, Block block, Action action) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 500);

        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            String name = block.getType().name();
            if (name.contains("OBSIDIAN") || name.contains("BEDROCK")) {
                Location spawn = block.getLocation().clone().add(0.5,1,0.5);
                for (Entity nearby : spawn.getWorld().getNearbyEntities(spawn,1,1,1)) {
                    if (nearby.getScoreboardTags().contains("§cTNT_CRYSTAL") && nearby != null && nearby.getWorld() == spawn.getWorld() && nearby.getLocation().distanceSquared(spawn) < 1) {
                        return;
                    }
                }
                if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                    OnClick.deductStack(player);
                }
                CustomArmorStands.tntCrystal(spawn);
            }
        }
    }
}
