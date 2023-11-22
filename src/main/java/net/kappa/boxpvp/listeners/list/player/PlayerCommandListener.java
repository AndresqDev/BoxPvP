package net.kappa.boxpvp.listeners.list.player;

import net.kappa.boxpvp.files.list.OptionsFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class PlayerCommandListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public static void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (player.isOp()) return;
        String cmd = event.getMessage().toLowerCase();
        if (cmd.contains(" ")) cmd = cmd.split(" ")[0];
        if (OptionsFile.blocked_commands.stream().anyMatch(cmd::equals)) {
            player.sendMessage(OptionsFile.blocked_commands_msg);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onTabComplete(PlayerCommandSendEvent event) {
        final Player player = event.getPlayer();
        if (player.isOp()) return;
        OptionsFile.blocked_commands.forEach(command -> {
            final String cmd = command.replace("/", "");
            if (event.getCommands().contains(cmd.replace("/", ""))) {
                player.sendMessage(OptionsFile.blocked_commands_msg);
                event.getCommands().remove(cmd);
            }
        });
    }
}
