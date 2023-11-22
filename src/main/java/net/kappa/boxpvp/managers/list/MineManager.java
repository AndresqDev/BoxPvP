package net.kappa.boxpvp.managers.list;

import net.kappa.boxpvp.utils.objects.LocationObject;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MineManager {
    private static final Map<Player, LocationObject> wands = new HashMap<>();

    public static LocationObject getWand(Player player) {
        return wands.get(player);
    }

    public static void removeWand(Player player) {
        wands.remove(player);
    }

    public static void putWand(Player player, LocationObject wand) {
        wands.put(player, wand);
    }

    public static boolean containsWand(Player player) {
        return !wands.containsKey(player);
    }
}
