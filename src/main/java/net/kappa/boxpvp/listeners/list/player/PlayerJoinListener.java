package net.kappa.boxpvp.listeners.list.player;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.managers.list.CombatManager;
import net.kappa.boxpvp.utils.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final boolean[] permission_found = {false};

        if (DataFile.combatloggers.contains(player.getUniqueId()))
            CombatManager.applyPunishment(player);

        event.setJoinMessage(null);
        player.setAllowFlight(true);

        for (int i = 0; i < 100; i++) player.sendMessage(" ");
        PlaceholderUtil.setPlaceholders(player, OptionsFile.message_join_format).forEach(player::sendMessage);
        OptionsFile.message_global_join_format.forEach(obj -> {
            if (permission_found[0]) return;
            if (player.hasPermission(obj.getPermission())) {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(PlaceholderUtil.setPlaceholders(player, obj.getMessage())));
                permission_found[0] = true;
            }
        });
    }
}
