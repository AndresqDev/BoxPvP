package lib.ziggurat.utils.playerversion;

import lib.ziggurat.utils.playerversion.impl.PlayerVersionProtocolLibImpl;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class PlayerVersionHandler {
    public static IPlayerVersion version;

    public PlayerVersionHandler() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        if (pluginManager.getPlugin("ProtocolLib") != null) {
            version = new PlayerVersionProtocolLibImpl();
        }
    }
}

