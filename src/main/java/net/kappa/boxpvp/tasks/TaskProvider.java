package net.kappa.boxpvp.tasks;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.tasks.list.ActionBarTask;
import net.kappa.boxpvp.tasks.list.ClearLagTask;
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

        OptionsFile.message_timed_objects.forEach(msg -> {
            final TimedMessageTask timedMessageTask = new TimedMessageTask(msg.getMessages());
            timedMessageTask.runTaskTimerAsynchronously(plugin, 5L, msg.getTime());
            this.tasks.add(timedMessageTask.getTaskId());
        });

        final ActionBarTask actionBarTask = new ActionBarTask();
        actionBarTask.runTaskTimerAsynchronously(plugin, 5L, 40L);
        this.tasks.add(actionBarTask.getTaskId());
    }

    public void disable() {
        tasks.forEach(i -> scheduler.cancelTask(i));
    }
}
