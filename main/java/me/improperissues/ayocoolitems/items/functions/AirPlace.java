package me.improperissues.ayocoolitems.items.functions;

import me.improperissues.ayocoolitems.other.Messages;
import me.improperissues.ayocoolitems.other.Sounds;
import me.improperissues.ayocoolitems.other.Vectors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class AirPlace {

    public static HashMap<String,Material> airplace = new HashMap<>();


    public static void function(Player player) {
        Material material = airplace.get(player.getName());
        if (material == null) {
            material = Material.BLACK_CONCRETE;
        }
        Location loc = Vectors.getEyeTargetVector(player,5);
        Block block = loc.getBlock();
        if (block.isPassable()) {
            block.setType(material);
            Sounds.playAll(player.getLocation(), Sound.BLOCK_STONE_PLACE,1,1,500);
        }
    }

    public static void airPlaceMenuClickEvent(Player player, ItemStack item) {
        airplace.put(player.getName(),item.getType());
        player.playSound(player.getLocation(),Sound.UI_BUTTON_CLICK,10,10);
        player.closeInventory();
        player.sendMessage(Messages.starter + "7Set your air place block type to §f" + item.getType().name().toLowerCase());
    }

    public static void openAirPlaceMenu(Player player) {
        Inventory menu = Bukkit.createInventory(player,54, Messages.starter + "1Airplace block type selection");

        for (Material material : Material.class.getEnumConstants()) {
            String name = material.name().toLowerCase();
            try {
                if ((name.contains("_concrete") && !name.contains("_powder"))
                        || (name.contains("terracotta") && !name.contains("glazed"))
                ) {
                    ItemStack item = new ItemStack(material);
                    menu.setItem(menu.firstEmpty(),item);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                break;
            }
        }

        player.openInventory(menu);
    }
}
