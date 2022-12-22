package me.improperissues.ayocoolitems.entity;

import me.improperissues.ayocoolitems.files.Files;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomArmorStands {

    public static void tntCrystal(Location location) {
        ArmorStand as = location.getWorld().spawn(location,ArmorStand.class);
        as.setInvisible(true);
        as.addScoreboardTag("§cTNT_CRYSTAL");
        as.setCustomName("TNT Crystal");
        as.setHelmet(new ItemStack(Material.TNT));
        as.setItemInHand(new ItemStack(Material.TNT));
        as.setGravity(false);
        as.setSmall(true);
        as.setSilent(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                as.setHeadPose(as.getHeadPose().add(-0.05D,0.1D,0.01D));
                as.setRightArmPose(as.getRightArmPose().add(0.05D,-0.1D,-0.01D));
            }
        }.runTaskTimer(Files.plugin,0,1);
    }
}
