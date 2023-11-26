package depends.ziggurat.tab;

import depends.ziggurat.ServerUtility;
import depends.ziggurat.tab.utils.IZigguratHelper;
import depends.ziggurat.tab.utils.impl.ProtocolLib4TabImpl;
import depends.ziggurat.tab.utils.impl.ProtocolLib5TabImpl;
import depends.ziggurat.utils.UtilityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static net.kappa.boxpvp.Main.pluginManager;

public class Ziggurat {
    private final JavaPlugin plugin;
    private final ZigguratAdapter adapter;
    private final Map<UUID, ZigguratTablistModern> tablists;
    private final Map<UUID, ZigguratTablistClassic> classictablists;
    private ZigguratThread thread;
    private IZigguratHelper implementation;
    private ZigguratListener listeners;

    public Ziggurat(JavaPlugin plugin, ZigguratAdapter adapter) {
        if (plugin == null) {
            throw new RuntimeException("Ziggurat can not be instantiated without a plugin instance!");
        }
        this.plugin = plugin;
        this.adapter = adapter;
        this.tablists = new ConcurrentHashMap<>();
        this.classictablists = new ConcurrentHashMap<>();
        UtilityManager.init();
        this.registerImplementation();
        this.setup();
    }

    private void registerImplementation() {
        final Plugin protocollib = pluginManager.getPlugin("ProtocolLib");
        if (protocollib != null) {
            if(protocollib.getDescription().getVersion().startsWith("4"))
                this.implementation = new ProtocolLib4TabImpl(this);
            else
                this.implementation = new ProtocolLib5TabImpl(this);
            this.plugin.getLogger().info("[Z-Tab] Registered Implementation with ProtocolLib");
            return;
        }
        this.plugin.getLogger().info("[Z-Tab] Unable to register Ziggurat with a proper implementation");
    }

    @SuppressWarnings("deprecation")
    private void setup() {
        this.listeners = new ZigguratListener(this);
        this.plugin.getServer().getPluginManager().registerEvents(this.listeners, this.plugin);
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        for (Player player : ServerUtility.getOnlinePlayers()) {
            if (this.getTablists().containsKey(player.getUniqueId())) continue;
            this.getTablists().put(player.getUniqueId(), new ZigguratTablistModern(player, this));
        }
        this.thread = new ZigguratThread(this);
    }

    @SuppressWarnings("deprecation")
    public void disable() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        if (this.listeners != null) {
            HandlerList.unregisterAll(this.listeners);
            this.listeners = null;
        }
        for (UUID uuid : this.getTablists().keySet()) {
            this.getTablists().get(uuid).cleanup();
        }
        this.getTablists().clear();
        this.implementation = null;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public ZigguratAdapter getAdapter() {
        return this.adapter;
    }

    public Map<UUID, ZigguratTablistModern> getTablists() {
        return this.tablists;
    }

    public Map<UUID, ZigguratTablistClassic> getTablistsClassic() {
        return this.classictablists;
    }

    public IZigguratHelper getImplementation() {
        return this.implementation;
    }

    public boolean isHook() {
        return false;
    }
}

