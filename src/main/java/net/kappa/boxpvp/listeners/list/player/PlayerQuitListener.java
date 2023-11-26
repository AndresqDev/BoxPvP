package net.kappa.boxpvp.listeners.list.player;

import net.kappa.boxpvp.managers.list.ClaimManager;
import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.utils.objects.ClaimObject;
import net.kappa.boxpvp.utils.objects.LoggerObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.kappa.boxpvp.Main.pseudoProtocol;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final ClaimObject claim = ClaimManager.getClaimAt(player);

        if (claim == null || claim.isPvP()) {
            if (pseudoProtocol == 16) new LoggerObject(player);
            else {
                player.setHealth(0.0D);
                return;
            }
            DataManager.addLogger(player.getUniqueId());
        }
    }
}
