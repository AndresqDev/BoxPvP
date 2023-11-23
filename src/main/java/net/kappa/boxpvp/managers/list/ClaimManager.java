package net.kappa.boxpvp.managers.list;

import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.utils.objects.ClaimObject;
import net.kappa.boxpvp.utils.objects.LocationObject;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ClaimManager {
    private static final Map<Player, LocationObject> wands = new HashMap<>();

    public static ClaimObject getClaimAt(Player player) {
        for (ClaimObject claim : DataFile.claims){
            if (claim.getCuboid().contains(player)) return claim;
        }

        return null;
    }

    public static ClaimObject getClaimAt(Block block) {
        for (ClaimObject claim : DataFile.claims){
            if (claim.getCuboid().contains(block)) return claim;
        }

        return null;
    }

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
