package net.kappa.boxpvp.listeners.list.player;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.utils.PlaceholderUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        if (OptionsFile.blocked_words.stream().anyMatch(event.getMessage()::contains)) {
            player.sendMessage(OptionsFile.blocked_word_msg);
            event.setCancelled(true);
        }
        event.setFormat(PlaceholderUtil.setPlaceholders(player, OptionsFile.others_chatformat_placeholder) + event.getMessage());
    }
}
