package me.improperissues.ayocoolitems.other;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Vectors {

    public static Location getTargetVector(Entity entity, double distance) {
        Vector vector = entity.getLocation().getDirection().normalize();
        double x = vector.getX() * distance;
        double y = vector.getY() * distance;
        double z = vector.getZ() * distance;
        return entity.getLocation().clone().add(x,y,z);
    }

    public static Location getEyeTargetVector(LivingEntity livingEntity, double distance) {
        Location loc = livingEntity.getEyeLocation();
        Vector dir = livingEntity.getLocation().getDirection();
        double x = dir.getX() * distance;
        double y = dir.getY() * distance;
        double z = dir.getZ() * distance;
        return new Location(loc.getWorld(),loc.getX() + x, loc.getY() + y, loc.getZ() + z);
    }

    public static Vector getVectorPoints(Location location1, Location location2) {
        return location1.toVector().subtract(location2.toVector()).normalize();
    }

    public static void drag(Entity entity, Location location) {
        entity.setVelocity(getVectorPoints(location,entity.getLocation()).multiply(2));
    }
}
