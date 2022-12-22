package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Displays;
import org.bukkit.entity.Player;

public class Shield {

    public static void function(Player player) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 2000);
        Displays.forceField(player,player.getLocation().clone().add(0,1,0));
    }
}
