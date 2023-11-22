package lib.fastboard;

import net.kappa.boxpvp.files.list.decoration.ScoreboardFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FastListener implements Listener {
    private final FastManager manager;

    public FastListener(FastManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final FastBoard newBoard = new FastBoard(player);
        if (!ScoreboardFile.anim_title_status) newBoard.updateTitle(ScoreboardFile.static_title);
        else newBoard.updateTitle(ScoreboardFile.animated_title.get(0));
        this.manager.add(player.getUniqueId(), newBoard);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.manager.remove(event.getPlayer().getUniqueId());
    }
}
