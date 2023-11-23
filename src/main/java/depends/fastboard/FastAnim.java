package depends.fastboard;

import net.kappa.boxpvp.files.list.decoration.ScoreboardFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static net.kappa.boxpvp.Main.plugin;
import static net.kappa.boxpvp.Main.scheduler;

public class FastAnim {
    private static final List<Integer> tasks = new ArrayList<>();
    public static String title;
    public static String footer;
    private final FastManager manager;
    private int loopTitle;
    private int loopFooter;

    public FastAnim(FastManager manager) {
        this.manager = manager;
    }

    public void start() {
        if (ScoreboardFile.anim_title_status) this.titleTask();
        if (ScoreboardFile.anim_footer_status && ScoreboardFile.footer_status) this.footerTask();
    }

    public void disable() {
        tasks.forEach(scheduler::cancelTask);
        tasks.clear();
        title = null;
        footer = null;
    }

    private void titleTask() {
        this.loopTitle = 0;

        tasks.add(scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if (Bukkit.getOnlinePlayers().isEmpty()) return;
            this.updateTitle();
            Bukkit.getOnlinePlayers().forEach(this::updatePlayerTitle);
        }, 0, ScoreboardFile.title_update_rate));
    }

    private void footerTask() {
        this.loopFooter = 0;
        footer = ScoreboardFile.animated_footer.get(0);

        tasks.add(scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if (Bukkit.getOnlinePlayers().isEmpty()) return;
            this.updateFooter();
            Bukkit.getOnlinePlayers().forEach(this::updatePlayerFooter);
        }, 0, ScoreboardFile.footer_update_rate));
    }

    private void updateTitle() {
        if (this.loopTitle == ScoreboardFile.animated_title.size() - 1) this.loopTitle = 0;
        else this.loopTitle++;

        title = ScoreboardFile.animated_title.get(this.loopTitle);
    }

    private void updateFooter() {
        if (this.loopFooter == ScoreboardFile.animated_footer.size() - 1) this.loopFooter = 0;
        else this.loopFooter++;

        footer = ScoreboardFile.animated_footer.get(this.loopFooter);
        ScoreboardFile.lines.set(ScoreboardFile.lines.size() - 1, footer);
    }

    private void updatePlayerTitle(Player player) {
        this.manager.get(player.getUniqueId()).updateTitle(title);
    }

    private void updatePlayerFooter(Player player) {
        final FastBoard score = this.manager.get(player.getUniqueId());
        if (score == null) return;

        int size;

        size = ScoreboardFile.lines.size();

        score.updateLine(size - 1, footer);
    }
}
