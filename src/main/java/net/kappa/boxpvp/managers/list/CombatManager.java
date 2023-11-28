package net.kappa.boxpvp.managers.list;

import org.bukkit.entity.Player;

public class CombatManager {
    public static void applyPunishment(Player player) {
        player.getInventory().clear();
        player.setHealth(0.0D);
    }

    public static void addPvPTag(Player player) {
        if (TimerManager.isActive(player, "pvp"))
            TimerManager.addTimer(player, "pvp", 30);
        else TimerManager.setRestant(player, "pvp", 30);
    }
}