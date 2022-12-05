package me.improperissues.ayocoolitems.commands;

import me.improperissues.ayocoolitems.events.OnClick;
import me.improperissues.ayocoolitems.files.Files;
import me.improperissues.ayocoolitems.files.UUIDLogs;
import me.improperissues.ayocoolitems.items.Items;
import me.improperissues.ayocoolitems.other.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;

        switch (command.getName().toLowerCase().trim()) {
            case "giveitem":
                try {
                    if (args.length >= 3) {
                        ItemStack item = Items.getItem(args[0]);
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
                        ItemStack item = Items.getItem(args[0]);
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
                        ItemStack item = Items.getItem(args[0]);

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
            case "killall-uuid":
                UUIDLogs.clearLog(p);
                return true;
            case "getall-uuid":
                p.sendMessage(Messages.starter + "cGetting logged entities, this may take up to minutes!");
                new BukkitRunnable() {
                    int dead = 0;
                    List<String> entities = new ArrayList<>();
                    int i = 0;
                    long timestarted = System.currentTimeMillis();
                    @Override
                    public void run() {
                        if (i < UUIDLogs.getLines().size()) {
                            for (int j = 0; j < 5; j ++) {
                                try {
                                    String uuid = UUIDLogs.getLines().get(i);
                                    try {
                                        Entity entity = Bukkit.getEntity(UUID.fromString(uuid));
                                        if (!entity.isDead()) entities.add(entity.getType().name().toLowerCase()); else dead ++;
                                    } catch (NullPointerException exception) {
                                        dead ++;
                                    }
                                    i ++;
                                } catch (IndexOutOfBoundsException exception) {
                                    // empty
                                }
                            }
                        } else {
                            double timefinished = Math.ceil((System.currentTimeMillis() - timestarted) / 10) / 100;
                            p.sendMessage(Messages.starter + "cCurrent entities are: " +
                                    "§7" + entities.toString() + "\n" + Messages.starter + "cThere are §e" +
                                    entities.size() + " §centities and §e" + dead + " §cdead entities! Proccess finished in §e" + timefinished + " §cseconds!");
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Files.plugin,0,1);
                return true;
        }

        return false;
    }
}
