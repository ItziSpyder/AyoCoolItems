package me.improperissues.ayocoolitems.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Messages {

    // §

    public static String starter = "§8[§cACI§8] §";

    public static void bm(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }
}
