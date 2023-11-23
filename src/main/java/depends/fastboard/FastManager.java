package depends.fastboard;

import depends.fastboard.thread.FastThread;
import net.kappa.boxpvp.files.list.decoration.ScoreboardFile;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static net.kappa.boxpvp.Main.plugin;

public class FastManager {
    private final FastThread fastThread;
    public static Map<UUID, FastBoard> boards = new ConcurrentHashMap<>();
    public static FastAnim fastAnim;

    public FastManager() {
        this.fastThread = new FastThread(this);
        this.fastThread.start();

        if (ScoreboardFile.footer_status) {
            if (!ScoreboardFile.anim_footer_status) {
                ScoreboardFile.lines.add(ScoreboardFile.static_footer);
            } else {
                final String first = ScoreboardFile.animated_footer.get(0);
                ScoreboardFile.lines.add(first);
            }
        }

        if (ScoreboardFile.anim_title_status || ScoreboardFile.anim_footer_status) {
            fastAnim = new FastAnim(this);
            fastAnim.start();
        }

        Bukkit.getPluginManager().registerEvents(new FastListener(this), plugin);
    }

    public void disable() {
        if (fastAnim != null) fastAnim.disable();
        this.fastThread.setRunning(false);
    }

    public void add(UUID uuid, FastBoard fastBoard) {
        boards.put(uuid, fastBoard);
    }

    public void remove(UUID uuid) {
        boards.remove(uuid);
    }

    public Map<UUID, FastBoard> getAll() {
        return boards;
    }

    public FastBoard get(UUID uuid) {
        return boards.get(uuid);
    }
}
