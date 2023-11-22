package lib.ziggurat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerUtility {
    public static List<Player> getOnlinePlayers() {
        return new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
    }
}

