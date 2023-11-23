package depends.ziggurat.utils.playerversion;

import org.bukkit.entity.Player;

public interface IPlayerVersion {
    PlayerVersion getPlayerVersion(Player var1);

    int getPlayerVersionRaw(Player var1);
}

