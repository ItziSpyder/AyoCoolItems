package me.improperissues.ayocoolitems.other;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.files.Files;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ReactionGame {

    public static HashMap<String,Long> reaction = new HashMap<>();

    public static void reactionGame(Player player) {
        if (reaction.containsKey(player.getName()) || (OnClick.clickCool.containsKey(player.getName()) && OnClick.clickCool.get(player.getName()) > System.currentTimeMillis())) {
            player.sendMessage("§cYou are already playing this game! Left click to end it!");
            return;
        }
        OnClick.clickCool.put(player.getName(), System.currentTimeMillis() + (5 * 1000));
        new BukkitRunnable() {
            int sec = 0;
            @Override
            public void run() {
                if (sec < 5) {
                    player.sendTitle("§e§l" + (5 - sec),"§eLeft click §fon §aGo §fto see your reaction result!",0,40,0);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING,10,1);
                    sec ++;
                } else {
                    player.sendTitle("§aGo!","§eLeft click §fnow!",0,40,0);
                    player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_PLING,10,1.5F);
                    reaction.put(player.getName(),System.currentTimeMillis());
                    this.cancel();
                }
            }
        }.runTaskTimer(Files.plugin,0,20);
    }

    public static void onInteraction(Player player) {
        if (reaction.containsKey(player.getName())) {
            double time = Math.floor((System.currentTimeMillis() - reaction.get(player.getName())) / 10) / 100;
            player.sendTitle("§b§l§oGG!","§bYour reaction time was §e" + time + " §bseconds!",0,40,0);
            player.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,10,10);
            Messages.bm("§e" + player.getName() + " §bplayed the reaction game and got a reaction time of §e" + time + " §b! GG! \n" +
                    "§bResults: §e" + time + " §bseconds, §e" + (System.currentTimeMillis() - reaction.get(player.getName())) +
                    " §bms! (Ping was §e" + player.getPing() + " §band tps was §e" + Main.tps + "§b)");
            reaction.remove(player.getName());
            return;
        }
    }
}
