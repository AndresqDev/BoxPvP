package net.kappa.boxpvp.listeners.list.player;

import net.kappa.boxpvp.managers.list.MineManager;
import net.kappa.boxpvp.utils.objects.LocationObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (!event.hasBlock()) return;
        if (event.getClickedBlock() == null) return;
        if (MineManager.containsWand(player)) return;

        final LocationObject wand = MineManager.getWand(player);

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            wand.setStart(event.getClickedBlock().getLocation());
            player.sendMessage(ChatColor.GREEN + "Mine pos 1 set!");
            player.sendMessage(ChatColor.RED + "Cancel selection using /mine again.");
            event.setCancelled(true);
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            wand.setLast(event.getClickedBlock().getLocation());
            player.sendMessage(ChatColor.GREEN + "Mine pos 2 set!");
            player.sendMessage(ChatColor.RED + "Cancel selection using /mine again.");
            event.setCancelled(true);
        }
    }
}
