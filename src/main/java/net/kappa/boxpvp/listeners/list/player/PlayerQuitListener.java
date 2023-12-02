package net.kappa.boxpvp.listeners.list.player;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.managers.list.TimerManager;
import net.kappa.boxpvp.utils.objects.LoggerObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (!TimerManager.isActive(player, "pvp")) {
            if (OptionsFile.combatlog_skeleton_entity) {
                new LoggerObject(player);
                DataManager.addLogger(player.getUniqueId());
            } else player.setHealth(0.0D);
        }
    }
}
