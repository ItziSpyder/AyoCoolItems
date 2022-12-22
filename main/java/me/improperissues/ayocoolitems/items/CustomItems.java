package me.improperissues.ayocoolitems.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomItems {

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
        setTNTCrystal();
        setFlintAndSteel();
    }

    public static ItemStack getItem(String string) {
        switch (string.toLowerCase().trim()) {
            case "fireball": return FIREBALL;
            case "wither": return WITHER;
            case "magnet": return MAGNET;
            case "melonizer": return MELONIZER;
            case "reversed_magnet": return REVERSE_MAGNET;
            case "thru": return THRU;
            case "rocket": return ROCKET;
            case "ash_wand": return ASH_WAND;
            case "tp_stick": return TP_STICK;
            case "improperimpressions": return IMPROPERIMPRESSIONS;
            case "air_place": return AIR_PLACE;
            case "taker": return TAKER;
            case "shield": return SHIELD;
            case "magneticrevolver": return MAGNETICREVOVLER;
            case "tazer": return TAZER;
            case "anime_sword": return ANIME_SWORD;
            case "falling_wand": return FALLING_WAND;
            case "immortality": return IMMORTALITY;
            case "tntcrystal": return TNTCRYSTAL;
            case "flintandsteal": return FLINTANDSTEEL;
        }
        return null;
    }

    public static ItemMeta addItemFlags(ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DYE,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
        return meta;
    }

    public static ItemStack FIREBALL;
    public static ItemStack WITHER;
    public static ItemStack ROCKET;
    public static ItemStack MAGNET;
    public static ItemStack REVERSE_MAGNET;
    public static ItemStack THRU;
    public static ItemStack MELONIZER;
    public static ItemStack ASH_WAND;
    public static ItemStack TP_STICK;
    public static ItemStack IMPROPERIMPRESSIONS;
    public static ItemStack AIR_PLACE;
    public static ItemStack TAKER;
    public static ItemStack SHIELD;
    public static ItemStack MAGNETICREVOVLER;
    public static ItemStack TAZER;
    public static ItemStack ANIME_SWORD;
    public static ItemStack FALLING_WAND;
    public static ItemStack IMMORTALITY;
    public static ItemStack TNTCRYSTAL;
    public static ItemStack FLINTANDSTEEL;


    static void setFlintAndSteel () {
        ItemStack item = new ItemStack(Material.FLINT_AND_STEEL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§7§lFlint N Steel§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Everything = TNT?",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        FLINTANDSTEEL = item;
    }
    static void setTNTCrystal () {
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§dTNT Crystal§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Works the same way a",
                "§7normal crystal would!",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        TNTCRYSTAL = item;
    }
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
        IMMORTALITY = item;
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
        ANIME_SWORD = item;
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
        FALLING_WAND = item;
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
        TAZER = item;
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
        MAGNETICREVOVLER = item;
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
        FIREBALL = item;
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
        WITHER = item;
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
        ROCKET = item;
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
        MAGNET = item;
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
        REVERSE_MAGNET = item;
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
        THRU = item;
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
        MELONIZER = item;
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
        ASH_WAND = item;
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
        TP_STICK = item;
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
        IMPROPERIMPRESSIONS = item;
    }

    static void setAir_place() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7§l<§d§cAir Place§7§l>");
        meta.setLore(new ArrayList<>(Arrays.asList(
                "§7Right click to place a ",
                "§7block in the air",
                "§7Left click to open the block  ",
                "§7selection menu",
                "",
                "§8§o#ayocoolitems"
        )));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.LUCK,1,false);

        item.setItemMeta(addItemFlags(meta));
        AIR_PLACE = item;
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
        TAKER = item;
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
        SHIELD = item;
    }
}
