package net.kappa.boxpvp.listeners.list.player;

import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.managers.list.TimerManager;
import net.kappa.boxpvp.utils.objects.LoggerObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.kappa.boxpvp.Main.version;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (!TimerManager.isActive(player, "pvp")) {
            if (version.contains("6")) {
                new LoggerObject(player);
                DataManager.addLogger(player.getUniqueId());
            }
            else {
                player.damage(5000000);
            }
        }
    }
}
