package net.kappa.boxpvp.utils.impl.rank;

import dev.rage.helium.api.HeliumAPI;
import net.kappa.boxpvp.utils.impl.RankInterface;
import org.bukkit.entity.Player;

public class HeliumImpl implements RankInterface {
    private final HeliumAPI instance;

    public HeliumImpl() {
        this.instance = HeliumAPI.INSTANCE;
    }

    @Override
    public String getGroupPrefix(Player player) {
        String prefix = this.instance.getRankPrefix(player.getUniqueId());

        if (prefix.equals("No data")) prefix = "";

        return prefix;
    }

    @Override
    public String getGroupName(Player player) {
        String name = this.instance.getRankName(player.getUniqueId());

        if (name.equals("No data")) name = "";

        return name;
    }
}
