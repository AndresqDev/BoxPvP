package net.kappa.boxpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;

public class LocationUtil {
    public static String toString(Location location) {
        return Objects.requireNonNull(location.getWorld()).getName() + " " + location.getX() + " " + location.getY() + " " + location.getZ() + " " + location.getYaw() + " " + location.getPitch();
    }

    public static Location toLocation(String string) {
        final String[] split = string.split(" ");
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }
}
