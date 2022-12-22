package me.improperissues.ayocoolitems.commands;

import me.improperissues.ayocoolitems.files.Warps;
import me.improperissues.ayocoolitems.other.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tabs implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();

        switch (command.getName().toLowerCase().trim()) {
            case "giveitem":
                switch (args.length) {
                    case 1:
                        return new ArrayList<>(Arrays.asList(
                                "fireball",
                                "wither",
                                "magnet",
                                "reversed_magnet",
                                "thru",
                                "rocket",
                                "melonizer",
                                "ash_wand",
                                "tp_stick",
                                "improperimpressions",
                                "air_place",
                                "taker",
                                "shield",
                                "magneticrevolver",
                                "tazer",
                                "anime_sword",
                                "falling_wand",
                                "immortality",
                                "tntcrystal",
                                "flintandsteal"
                                ));
                    case 2:
                        for (int i = 0; i < 10; i ++) {
                            list.add(String.valueOf(i + 1));
                        }
                        break;
                    case 3:
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            list.add(online.getName());
                        }
                        break;
                }
                break;
            case "velocity":
                switch (args.length) {
                    case 1:
                        return ServerUtils.getPlayers();
                    case 2:
                        list.add("^ ^ ^");
                        list.add("1 1 1");
                        break;
                    case 3:
                        list.add("^ ^");
                        list.add("1 1");
                        break;
                    case 4:
                        list.add("^");
                        list.add("1");
                        break;
                }
                break;
            case "delwarp":
            case "warp":
                return Warps.listWarps();
            case "addwarp":
                list.add("§8<name: string>");
                break;
        }

        return list;
    }
}
