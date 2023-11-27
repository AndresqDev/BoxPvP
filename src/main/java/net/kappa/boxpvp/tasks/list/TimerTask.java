package net.kappa.boxpvp.tasks.list;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerTask extends BukkitRunnable {
    private final Player player;
    private final String identifier;
    private int time;
    private boolean enabled;

    public TimerTask(String identifier, Player player, int time) {
        this.player = player;
        this.identifier = identifier;
        this.time = time * 20;
        this.enabled = true;
    }

    @Override
    public void run() {
        if (this.time/20 == 0) {
            this.enabled = false;
            this.cancel();
        }
        this.time -= 20;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTime() {
        return String.valueOf(this.time/20);
    }

    public void setTime(int time) {
        this.time = time * 20;
    }
}
