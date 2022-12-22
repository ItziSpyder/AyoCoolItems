package me.improperissues.ayocoolitems.other;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;

public class PlayerTitles {

    private static int r = 0;
    private static int g = 0;
    private static int b = 0;

    public static void rainbowRGB() {
        for (int i = 0; i < 10; i ++) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getScoreboardTags().contains("§8RGB_DISPLAY")) {
                    p.setPlayerListName(gradient("[IMPROPER] ", new Color(r, g, b)) + gradient(p.getName(), new Color(255-r, 255-g, 255-b)));
                    p.setDisplayName(gradient("[IMPROPER] ", new Color(r, g, b)) + gradient(p.getName(), new Color(255-r, 255-g, 255-b)));
                    p.setCustomName(gradient("[IMPROPER] ", new Color(r, g, b)) + gradient(p.getName(), new Color(255-r, 255-g, 255-b)));
                }
            }
            if (r < 255 && g == 0 && b == 0) r ++;
            if (r == 255 && g < 255 && b == 0) g ++;
            if (r == 255 && g == 255 && b < 255) b ++;
            if (r > 0 && g == 255 && b == 255) r --;
            if (r == 0 && g > 0 && b == 255) g --;
            if (r == 0 && g == 0 && b > 0) b --;
        }
    }

    public static String gradient(String text, Color current) {
        char[] chars = text.toCharArray();
        int[] rgb = {current.getRed(),current.getGreen(),current.getBlue()};

        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            builder.append(ChatColor.of(new Color(rgb[0],rgb[1],rgb[2]))).append(c);
        }
        return String.valueOf(builder);
    }
}
