package net.kappa.boxpvp.managers.list;

import io.netty.util.internal.ConcurrentSet;
import net.kappa.boxpvp.tasks.list.TimerTask;
import org.bukkit.entity.Player;

import static net.kappa.boxpvp.Main.plugin;

public class TimerManager {
    private static final ConcurrentSet<TimerTask> timers = new ConcurrentSet<>();

    public static void addTimer(Player player, String identifier, int time) {
        final TimerTask timer = new TimerTask(identifier, player, time);
        timer.runTaskTimerAsynchronously(plugin, 20L, 20L);
        timers.add(timer);
        plugin.taskProvider.addTask(timer.getTaskId());
    }

    public static boolean isActive(Player player, String identifier) {
        for (TimerTask timer : timers) {
            if (timer.getIdentifier().equals(identifier)
                    && timer.getPlayer() == player) {
                if (timer.isEnabled()) {
                    return !timer.isEnabled();
                } else {
                    timers.remove(timer);
                    break;
                }
             }
        }

        return true;
    }

    public static String getRestant(Player player, String identifier) {
        for (TimerTask timer : timers) {
            if (timer.getIdentifier().equals(identifier)
                    && timer.getPlayer() == player) return timer.getTime();
        }

        return "0";
    }

    public static void setRestant(Player player, String identifier, int time) {
        for (TimerTask timer : timers) {
            if (timer.getIdentifier().equals(identifier)
                    && timer.getPlayer() == player) timer.setTime(time);
        }
    }
}
