package lib.ziggurat.tab;

import net.kappa.boxpvp.files.list.decoration.TabFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ZigguratListener implements Listener {
    private final Ziggurat instance;

    public ZigguratListener(Ziggurat instance) {
        this.instance = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (TabFile.tab_type.equals("modern"))
            this.instance.getTablists().put(player.getUniqueId(), new ZigguratTablistModern(player, this.instance));
        else
            this.instance.getTablistsClassic().put(player.getUniqueId(), new ZigguratTablistClassic(player, this.instance));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if (TabFile.tab_type.equals("modern"))
            this.instance.getTablists().remove(player.getUniqueId());
        else
            this.instance.getTablistsClassic().remove(player.getUniqueId());
    }
}

