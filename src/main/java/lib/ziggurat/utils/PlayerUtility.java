package lib.ziggurat.utils;

import lib.ziggurat.utils.playerversion.PlayerVersion;
import lib.ziggurat.utils.playerversion.PlayerVersionHandler;
import org.bukkit.entity.Player;

public class PlayerUtility {

    public static PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersionHandler.version.getPlayerVersion(player);
    }

    public static int getPlayerVersionRaw(Player player) {
        return PlayerVersionHandler.version.getPlayerVersionRaw(player);
    }
}

