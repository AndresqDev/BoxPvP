package depends.ziggurat.utils;

import depends.ziggurat.utils.playerversion.PlayerVersion;
import depends.ziggurat.utils.playerversion.PlayerVersionHandler;
import org.bukkit.entity.Player;

public class PlayerUtility {

    public static PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersionHandler.version.getPlayerVersion(player);
    }

    public static int getPlayerVersionRaw(Player player) {
        return PlayerVersionHandler.version.getPlayerVersionRaw(player);
    }
}

