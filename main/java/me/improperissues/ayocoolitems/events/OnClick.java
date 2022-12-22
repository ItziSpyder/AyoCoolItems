package me.improperissues.ayocoolitems.events;

import me.improperissues.ayocoolitems.items.CustomItems;
import me.improperissues.ayocoolitems.items.functions.*;
import me.improperissues.ayocoolitems.other.Displays;
import me.improperissues.ayocoolitems.other.Messages;
import me.improperissues.ayocoolitems.other.ReactionGame;
import me.improperissues.ayocoolitems.other.Vectors;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnClick implements Listener {

    public static HashMap<String,Long> clickCool = new HashMap<>();
    public static List<String> outlineBelow = new ArrayList<>();
    public static List<String> highlightVector = new ArrayList<>();


    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        // Event functions
        ReactionGame.onInteraction(p);

        // Items and interaction
        try {
            Action action = e.getAction();
            Block block = e.getClickedBlock();
            ItemStack item = getClickedItem(p);
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (!isCustom(item)) {
                return;
            }
            e.setCancelled(true);
            if (clickCool.containsKey(p.getName()) && clickCool.get(p.getName()) > System.currentTimeMillis()) {
                return;
            }
            clickCool.put(p.getName(),System.currentTimeMillis() + 50);

            if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (containsName(display, CustomItems.AIR_PLACE)) AirPlace.openAirPlaceMenu(p);
                return;
            }

            if (containsName(display, CustomItems.FIREBALL)) Fireball.function(p,item);
            else if (containsName(display, CustomItems.WITHER)) Wither.function(p,item);
            else if (containsName(display, CustomItems.THRU)) Thru.function(p);
            else if (containsName(display, CustomItems.ROCKET)) Rocket.function(p);
            else if (containsName(display, CustomItems.MAGNET)) Magnet.function(p);
            else if (containsName(display, CustomItems.REVERSE_MAGNET)) ReverseMagnet.function(p);
            else if (containsName(display, CustomItems.ASH_WAND)) AshWand.function(p);
            else if (containsName(display, CustomItems.TP_STICK)) TpStick.function(p);
            else if (containsName(display, CustomItems.IMPROPERIMPRESSIONS)) ImproperImpressions.function(p);
            else if (containsName(display, CustomItems.AIR_PLACE)) AirPlace.function(p);
            else if (containsName(display, CustomItems.SHIELD)) Shield.function(p);
            else if (containsName(display, CustomItems.TAZER)) Tazer.function(p);
            else if (containsName(display, CustomItems.MAGNETICREVOVLER)) MagneticRevolver.function(p);
            else if (containsName(display, CustomItems.FALLING_WAND)) FallingWand.function(p);
            else if (containsName(display, CustomItems.IMMORTALITY)) Immortality.function(p);
            else if (containsName(display, CustomItems.TNTCRYSTAL)) TNTCrystal.function(p,block,action);
            if (containsName(display, CustomItems.FLINTANDSTEEL)) FlintAndSteel.function(p,block,action);
        } catch (NullPointerException exception) {
            // empty
        }
    }


    @EventHandler
    public static void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();

        if (!(damager instanceof Player)) {
            return;
        }
        Player p = (Player) damager;
        if (clickCool.containsKey(p.getName()) && clickCool.get(p.getName()) > System.currentTimeMillis()) {
            return;
        }
        clickCool.put(p.getName(),System.currentTimeMillis() + 50);

        try {
            ItemStack item = getClickedItem(p);
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (containsName(display, CustomItems.ANIME_SWORD)) AnimeSword.function(p,entity);
        } catch (NullPointerException exception) {
            // empty
        }
    }

    @EventHandler
    public static void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        Entity entity = e.getRightClicked();

        try {
            ItemStack item = getClickedItem(p);
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (!isCustom(item)) {
                return;
            }
            e.setCancelled(true);
            if (clickCool.containsKey(p.getName()) && clickCool.get(p.getName()) > System.currentTimeMillis()) {
                return;
            }
            clickCool.put(p.getName(),System.currentTimeMillis() + 50);

            if (containsName(display, CustomItems.MELONIZER)) Melonizer.function(p,entity);
            else if (containsName(display, CustomItems.TAKER)) Taker.function(p,entity);
        } catch (NullPointerException | IllegalArgumentException | ClassCastException exception) {
            // empty
        }
    }


    public static boolean containsName(String name, ItemStack item) {
        return name.contains(item.getItemMeta().getDisplayName());
    }

    @EventHandler
    public static void PlayerMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (Melonizer.isMelonized(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void PlayerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        if (p != null && Melonizer.isMelonized(p)) {
            Messages.bm("§a" + p.getName() + " §2was squished and died");
        }
    }

    public static ItemStack getClickedItem(Player player) {
        ItemStack main = player.getInventory().getItemInMainHand();
        ItemStack off = player.getInventory().getItemInOffHand();

        if (isCustom(main)) {
            return main;
        } else {
            return off;
        }
    }

    public static void deductStack(Player player) {
        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            getClickedItem(player).setAmount(getClickedItem(player).getAmount() - 1);
        }
    }

    public static boolean isCustom(ItemStack item) {
        try {
            return item.getItemMeta().getLore().contains("§8§o#ayocoolitems");
        } catch (NullPointerException exception) {
            return false;
        }
    }

    public static void registerEvents() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (outlineBelow.contains(p.getName())) {
                if (p.isOnGround()) Displays.outline(p.getLocation().add(0,-1,0));
            }
            if (highlightVector.contains(p.getName())) {
                Displays.draw(p.getEyeLocation(),Vectors.getEyeTargetVector(p,4.5),Color.BLUE);
                Displays.outline(Vectors.getEyeTargetVector(p,4.5));
            }
            try {
                ItemStack item = getClickedItem(p);
                ItemMeta meta = item.getItemMeta();
                String display = meta.getDisplayName();

                if (display.contains(CustomItems.AIR_PLACE.getItemMeta().getDisplayName())) {
                    Displays.outline(Vectors.getEyeTargetVector(p,5));
                }
            } catch (NullPointerException exception) {
                // empty
            }
        }
    }


    @EventHandler
    public static void InventionClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        String title = e.getView().getTitle();

        try {
            if (title.contains(Messages.starter) && !inv.getType().equals(InventoryType.PLAYER)) {
                e.setCancelled(true);
                ItemStack item = e.getCurrentItem();
                ItemMeta meta = item.getItemMeta();

                if (title.contains("1Airplace block type selection")) {
                    AirPlace.airPlaceMenuClickEvent(p,item);
                }
            }
        } catch (NullPointerException exception) {
            // empty
        }
    }
}
