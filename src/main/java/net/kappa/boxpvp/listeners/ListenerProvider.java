package net.kappa.boxpvp.listeners;

import net.kappa.boxpvp.listeners.list.player.*;
import net.kappa.boxpvp.listeners.list.system.WorldInteractionsListener;
import org.bukkit.plugin.PluginManager;

import static net.kappa.boxpvp.Main.plugin;

public class ListenerProvider {
    public ListenerProvider() {
        this.setup();
    }

    private void setup() {
        final PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerChatListener(), plugin);
        pm.registerEvents(new PlayerCommandListener(), plugin);
        pm.registerEvents(new PlayerInteractListener(), plugin);
        pm.registerEvents(new PlayerJoinListener(), plugin);
        pm.registerEvents(new PlayerQuitListener(), plugin);
        pm.registerEvents(new WorldInteractionsListener(), plugin);
    }
}
