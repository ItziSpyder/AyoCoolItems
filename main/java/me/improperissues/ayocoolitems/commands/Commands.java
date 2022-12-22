package me.improperissues.ayocoolitems.commands;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.files.UUIDLogs;
import me.improperissues.ayocoolitems.files.Warps;
import me.improperissues.ayocoolitems.items.CustomItems;
import me.improperissues.ayocoolitems.other.Messages;
import me.improperissues.ayocoolitems.other.ReactionGame;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Console or player commands
        switch (command.getName().toLowerCase().trim()) {
            case "velocity":
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null && args.length > 1) {
                    sender.sendMessage("§cThat player is either offline or null!");
                    return false;
                }
                try {
                    if (args[1].contains("^")) {
                        Double vel = target.getLocation().getDirection().getX();
                        try {
                            int mult = Integer.parseInt(args[1].replaceAll("\\^",""));
                            args[1] = (mult > 10 ? args[1] : String.valueOf(vel * mult));
                        } catch (NumberFormatException exception) {
                            args[1] = String.valueOf(vel);
                        }

                    }
                    if (args[2].contains("^")) {
                        Double vel = target.getLocation().getDirection().getY();
                        try {
                            int mult = Integer.parseInt(args[2].replaceAll("\\^",""));
                            args[2] = (mult > 10 ? args[2] : String.valueOf(vel * mult));
                        } catch (NumberFormatException exception) {
                            args[2] = String.valueOf(vel);
                        }
                    }
                    if (args[3].contains("^")) {
                        Double vel = target.getLocation().getDirection().getZ();
                        try {
                            int mult = Integer.parseInt(args[3].replaceAll("\\^",""));
                            args[3] = (mult > 10 ? args[3] : String.valueOf(vel * mult));
                        } catch (NumberFormatException exception) {
                            args[3] = String.valueOf(vel);
                        }
                    }
                    double x = 0;
                    double y = 0;
                    double z = 0;
                    x = Double.parseDouble(args[1]);
                    y = Double.parseDouble(args[2]);
                    z = Double.parseDouble(args[3]);
                    target.setVelocity(new Vector(x,y,z));
                    sender.sendMessage("§fSet the velocity of player §7" + target.getName() + " §fto [§7" + x + "§f,§7" + y + "§f,§7" + z + "§f]");
                    return true;
                } catch (IllegalArgumentException | NullPointerException | IndexOutOfBoundsException exception) {
                    return false;
                }
        }


        // Player commands
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;

        try {
            switch (command.getName().toLowerCase().trim()) {
                case "giveitem":
                    try {
                        if (args.length >= 3) {
                            ItemStack item = CustomItems.getItem(args[0]);
                            Integer amount = Integer.parseInt(args[1]);
                            Player target = Bukkit.getPlayer(args[2]);

                            if (amount > 6400) {
                                sender.sendMessage(Messages.starter + "cYou cannot give over 6400 items at once!");
                                return false;
                            }

                            for (int i = 0; i < amount; i ++) {
                                target.getInventory().addItem(item);
                            }

                            sender.sendMessage(Messages.starter + "7Gave §fx" + amount + " " + args[0].toLowerCase().trim() + " §7to §f" + target.getName() + " §7!");
                            target.sendMessage(Messages.starter + "7You were given §fx" + amount + " " + args[0].toLowerCase().trim() + " §7!");
                            return true;
                        } else if (args.length == 2) {
                            ItemStack item = CustomItems.getItem(args[0]);
                            Integer amount = Integer.parseInt(args[1]);

                            if (amount > 6400) {
                                sender.sendMessage(Messages.starter + "cYou cannot give over 6400 items at once!");
                                return false;
                            }

                            for (int i = 0; i < amount; i ++) {
                                p.getInventory().addItem(item);
                            }

                            sender.sendMessage(Messages.starter + "7Gave §fx" + amount + " " + args[0].toLowerCase().trim() + " §7!");
                            return true;
                        } else if (args.length == 1) {
                            ItemStack item = CustomItems.getItem(args[0]);

                            p.getInventory().addItem(item);

                            sender.sendMessage(Messages.starter + "7Gave §f" + args[0].toLowerCase().trim() + " §7!");
                            return true;
                        }
                    } catch (IllegalArgumentException | NullPointerException exception) {
                        return false;
                    }
                    break;
                case "toggleoutline":
                    if (OnClick.outlineBelow.contains(sender.getName())) {
                        OnClick.outlineBelow.remove(sender.getName());
                        sender.sendMessage(Messages.starter + "cYou will no longer outline the block under you");
                    } else {
                        OnClick.outlineBelow.add(sender.getName());
                        sender.sendMessage(Messages.starter + "aYou will now outline the block under you");
                    }
                    return true;
                case "togglevectorhighlight":
                    if (OnClick.highlightVector.contains(sender.getName())) {
                        OnClick.highlightVector.remove(sender.getName());
                        sender.sendMessage(Messages.starter + "cYou will no longer highlight your look vector!");
                    } else {
                        OnClick.highlightVector.add(sender.getName());
                        sender.sendMessage(Messages.starter + "aYou will now highlight your look vector!");
                    }
                    return true;
                case "killall-uuid":
                    UUIDLogs.clearLog(p);
                    return true;
                case "getall-uuid":
                    UUIDLogs.getallUUID(p);
                    return true;
                case "reaction":
                    ReactionGame.reactionGame(p);
                    return true;
                case "addwarp":
                    Warps.addWarp(args[0].toLowerCase(),p.getLocation());
                    p.sendMessage(Messages.starter + "§dCreated a new warp " + args[0].toLowerCase());
                    return true;
                case "delwarp":
                    Warps.removeWarp(args[0].toLowerCase());
                    p.sendMessage(Messages.starter + "§dDeleted warp " + args[0].toLowerCase());
                    return true;
                case "warp":
                    Location loc = Warps.getWarp(args[0].toLowerCase());
                    p.teleport(loc);
                    p.sendMessage(Messages.starter + "§dWarped you to " + args[0].toLowerCase());
                    return true;
                case "rgb-display":
                    if (p.getScoreboardTags().contains("§8RGB_DISPLAY")) {
                        p.removeScoreboardTag("§8RGB_DISPLAY");
                        p.sendMessage(Messages.starter + "dRemoved your rgb display!");
                        p.setPlayerListName(p.getName());
                        p.setDisplayName(p.getName());
                        p.setCustomName(p.getName());
                        return true;
                    }
                    p.addScoreboardTag("§8RGB_DISPLAY");
                    p.sendMessage(Messages.starter + "dAdded rgb display to your name!");
                    return true;
                case "spawn":
                    p.chat("/warp spawn");
                    return true;
            }
        } catch (Exception exception) {
            p.sendMessage(Messages.starter + "4An error has occurred: §e" + exception);
            p.sendMessage(Messages.starter + "4Caused by: §e" + exception.getCause());
        }

        return false;
    }
}
