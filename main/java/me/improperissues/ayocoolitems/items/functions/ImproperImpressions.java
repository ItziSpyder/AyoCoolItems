package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Displays;
import org.bukkit.entity.Player;

public class ImproperImpressions {

    public static void function(Player player) {
        OnClick.clickCool.put(player.getName(),System.currentTimeMillis() + 5000);
        Displays.storm(player.getLocation());
    }
}
