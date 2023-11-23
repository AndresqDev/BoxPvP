package net.kappa.boxpvp.utils.impl.rank;

import net.kappa.boxpvp.utils.impl.RankInterface;
import org.bukkit.entity.Player;

public class VoidImpl implements RankInterface {
    @Override
    public String getGroupPrefix(Player player) {
        return "";
    }

    @Override
    public String getGroupName(Player player) {
        return "";
    }
}
