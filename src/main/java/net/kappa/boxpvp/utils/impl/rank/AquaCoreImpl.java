package net.kappa.boxpvp.utils.impl.rank;

import me.activated.core.plugin.AquaCoreAPI;
import net.kappa.boxpvp.utils.impl.RankInterface;
import org.bukkit.entity.Player;

public class AquaCoreImpl implements RankInterface {
    private final AquaCoreAPI instance;

    public AquaCoreImpl() {
        this.instance = AquaCoreAPI.INSTANCE;
    }

    @Override
    public String getGroupPrefix(Player player) {
        return this.instance.getPlayerRank(player.getUniqueId()).getPrefix();
    }

    @Override
    public String getGroupName(Player player) {
        return this.instance.getPlayerRank(player.getUniqueId()).getName();
    }
}
