package net.kappa.boxpvp.managers.list;

import org.bukkit.entity.Player;

public class CombatManager {
    public static void applyPunishment(Player player) {
        player.getInventory().clear();
        player.setHealth(0.0D);
    }
}
