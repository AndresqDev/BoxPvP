package net.kappa.boxpvp.managers.list;

import me.activated.core.api.rank.RankData;
import me.activated.core.api.rank.grant.Grant;
import net.kappa.boxpvp.utils.presets.APIPreset;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.UUID;

public class RankManager {
    public static String getPrefixOf(UUID uuid) {
        final String prefix = APIPreset.AquaRankAPI.getPlayerRank(uuid).getPrefix();
        if (prefix == null || prefix.equals("")) return ChatColor.GRAY + "User";
        return prefix;
    }

    public static String getExpireOf(UUID uuid) {
        final List<Grant> activeGrants = APIPreset.AquaRankAPI.getPlayerData(uuid).getActiveGrants();
        if (activeGrants == null || activeGrants.size() == 0) return ChatColor.GRAY + "Never";

        return activeGrants.get(activeGrants.size() - 1).getNiceDuration();
    }

    public static RankData getRankOf(UUID uuid) {
        return APIPreset.AquaRankAPI.getPlayerRank(uuid);
    }
}
