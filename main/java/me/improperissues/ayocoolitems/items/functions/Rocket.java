package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.other.Sounds;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Rocket {

    public static void function(Player player) {
        OnClick.deductStack(player);
        Sounds.playAll(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,1,1,500);
        player.setVelocity(player.getLocation().getDirection().multiply(3));
    }
}
