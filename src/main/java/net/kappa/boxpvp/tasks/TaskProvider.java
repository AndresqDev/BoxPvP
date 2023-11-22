package net.kappa.boxpvp.tasks;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.tasks.list.ActionBarTask;
import net.kappa.boxpvp.tasks.list.ClearLagTask;
import net.kappa.boxpvp.tasks.list.MineRegenTask;
import net.kappa.boxpvp.tasks.list.TimedMessageTask;

import java.util.ArrayList;
import java.util.List;

import static net.kappa.boxpvp.Main.plugin;
import static net.kappa.boxpvp.Main.scheduler;

public class TaskProvider {
    private final List<Integer> tasks = new ArrayList<>();

    public TaskProvider() {
        this.setup();
    }

    private void setup() {
        final ClearLagTask lagTask = new ClearLagTask();
        lagTask.runTaskTimerAsynchronously(plugin, OptionsFile.others_clearlag_delay, OptionsFile.others_clearlag_delay);
        this.tasks.add(lagTask.getTaskId());

        if (!DataFile.mines.isEmpty()) {
            DataFile.mines.forEach(m -> {
                final MineRegenTask regenTask = new MineRegenTask(m);
                regenTask.runTaskTimer(plugin, 20L, m.getTime());
                this.tasks.add(regenTask.getTaskId());
            });
        }

        OptionsFile.message_timed_objects.forEach(msg -> {
            final TimedMessageTask timedMessageTask = new TimedMessageTask(msg.getMessages());
            timedMessageTask.runTaskTimerAsynchronously(plugin, 5L, msg.getTime());
            this.tasks.add(timedMessageTask.getTaskId());
        });

        final ActionBarTask actionBarTask = new ActionBarTask();
        actionBarTask.runTaskTimerAsynchronously(plugin, 5L, 40L);
        this.tasks.add(actionBarTask.getTaskId());
    }

    public void addTask(Integer ID) {
        this.tasks.add(ID);
    }

    public void disable() {
        this.tasks.forEach(i -> scheduler.cancelTask(i));
    }
}
