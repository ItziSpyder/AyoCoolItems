package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Raycast;
import org.bukkit.entity.Player;

public class MagneticRevolver {

    public static void function(Player player) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 2000);
        Raycast.magneticRevolver(player);
    }
}
