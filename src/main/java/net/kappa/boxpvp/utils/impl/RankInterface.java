package net.kappa.boxpvp.utils.impl;

import org.bukkit.entity.Player;

public interface RankInterface {
    String getGroupPrefix(Player player);

    String getGroupName(Player player);
}
