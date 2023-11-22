package lib.fastboard.thread;

import lib.fastboard.FastManager;
import net.kappa.boxpvp.files.list.decoration.ScoreboardFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FastThread extends Thread {
    private final FastManager manager;
    private boolean running;

    public FastThread(FastManager manager) {
        this.manager = manager;
        this.running = true;
    }

    public void setRunning(boolean value) {
        this.running = value;
    }

    @SuppressWarnings("all")
    @Override
    public void run() {
        try {
            while (this.running) {
                this.manager.getAll().forEach((uuid, board) -> {
                    final Player player = Bukkit.getPlayer(uuid);
                    board.updateLines(ScoreboardFile.lines);
                });
                Thread.sleep(ScoreboardFile.board_update_rate);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
