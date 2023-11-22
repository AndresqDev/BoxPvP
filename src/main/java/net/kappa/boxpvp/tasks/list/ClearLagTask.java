package net.kappa.boxpvp.tasks.list;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class ClearLagTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getWorlds().forEach(w -> w.getEntities().forEach(e -> {
            if(e instanceof Item) e.remove();
        }));
    }
}
