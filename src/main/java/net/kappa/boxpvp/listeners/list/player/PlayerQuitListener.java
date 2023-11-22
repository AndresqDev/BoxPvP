package net.kappa.boxpvp.listeners.list.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
    }
}
