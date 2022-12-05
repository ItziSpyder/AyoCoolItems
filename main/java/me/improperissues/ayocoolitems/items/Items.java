package me.improperissues.ayocoolitems.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Items {

    // §

    public static void registerItems() {
        setFireball();
        setWither();
        setMagnet();
        setMelonizer();
        setReverse_magnet();
        setThru();
        setRocket();
        setAsh_wand();
        setTp_stick();
        setImproperimpressions();
        setAir_place();
        setTaker();
        setShield();
        setMagneticRevolver();
        setTazer();
        setAnime_sword();
        setFalling_wand();
        setImmortality();
    }

    public static ItemStack getItem(String string) {
        switch (string.toLowerCase().trim()) {
            case "fireball":
                return fireball;
            case "wither":
                return wither;
            case "magnet":
                return magnet;
            case "melonizer":
                return melonizer;
            case "reversed_magnet":
                return reverse_magnet;
            case "thru":
                return thru;
            case "rocket":
                return rocket;
            case "ash_wand":
                return ash_wand;
            case "tp_stick":
                return tp_stick;
            case "improperimpressions":
                return improperimpressions;
            case "air_place":
                return air_place;
            case "taker":
                return taker;
            case "shield":
                return shield;
            case "magneticrevolver":
                return magneticRevolver;
            case "tazer":
                return tazer;
            case "anime_sword":
                return anime_sword;
            case "falling_wand":
                return falling_wand;
            case "immortality":
                return immortality;
        }
        return null;
    }

    public static ItemMeta addItemFlags(ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DYE,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        return meta;
    }

    public static ItemStack fireball;
    public static ItemStack wither;
    public static ItemStack rocket;
    public static ItemStack magnet;
    public static ItemStack reverse_magnet;
    public static ItemStack thru;
    public static ItemStack melonizer;
    public static ItemStack ash_wand;
    public static ItemStack tp_stick;
    public static ItemStack improperimpressions;
    public static ItemStack air_place;
    public static ItemStack taker;
    public static ItemStack shield;
    public static ItemStack magneticRevolver;
    public static ItemStack tazer;
    public static ItemStack anime_sword;
    public static ItemStack falling_wand;
    public static ItemStack immortality;


    static void setImmortality() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§cThe Elixer of Life§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Grants immortality!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        immortality = item;
    }
    static void setAnime_sword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§f§lANIME SWORD§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7\"behind you\"",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        anime_sword = item;
    }

    static void setFalling_wand() {
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§3Wand of Falling Blocks§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click a block to turn",
                "§7it into a falling block!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        falling_wand = item;
    }

    static void setTazer() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§bTazer§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Zzzap!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        tazer = item;
    }

    static void setMagneticRevolver() {
        ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§fMagnetic Revolver§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7The bullets are magnetic!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        magneticRevolver = item;
    }

    static void setFireball() {
        ItemStack item = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§6Fireball§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click this item to",
                "§7throw a fireball!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        fireball = item;
    }

    static void setWither() {
        ItemStack item = new ItemStack(Material.WITHER_SKELETON_SKULL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§8Wither Skull§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click this item to",
                "§7throw a wither skull!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        wither = item;
    }

    static void setRocket() {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§cRocket§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click this item to",
                "§7launch yourself!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        rocket = item;
    }

    static void setMagnet() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§7Magnet§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click this item to",
                "§7attract players!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        magnet = item;
    }

    static void setReverse_magnet() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§eReverse Magnet§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click this item to",
                "§7be repel players!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        reverse_magnet = item;
    }

    static void setThru() {
        ItemStack item = new ItemStack(Material.GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§bThru§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click this item to",
                "§7set your gamemode to spectator!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        thru = item;
    }

    static void setMelonizer() {
        ItemStack item = new ItemStack(Material.MELON_SLICE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§aMelonizer§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click an entity with",
                "§7this item to melonize them!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        melonizer = item;
    }

    static void setAsh_wand() {
        ItemStack item = new ItemStack(Material.LEVER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§8The Ash Wand§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click to shoot a deadly",
                "§7ray, turning all targets within",
                "§7a range of impact into dust...",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        ash_wand = item;
    }

    static void setTp_stick() {
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§dTeleportation Stick§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click to teleport a",
                "§7few blocks forward!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        tp_stick = item;
    }

    static void setImproperimpressions() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§d§lImproperImpressions§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click for an",
                "§7improper impression",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        improperimpressions = item;
    }

    static void setAir_place() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§d§cAir Place§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click to place a ",
                "§7block in the air",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        air_place = item;
    }

    static void setTaker() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§d§cTaker§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click an entity to",
                "§7feed it to the dark soul",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        taker = item;
    }

    static void setShield() {
        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§bForce Field§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click an entity to",
                "§7spawn a protective shield",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        shield = item;
    }
}
