package net.kappa.boxpvp.tasks.list;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TimedMessageTask extends BukkitRunnable {
    private final List<String> msg;

    public TimedMessageTask(List<String> msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        this.msg.forEach(Bukkit::broadcastMessage);
    }
}
