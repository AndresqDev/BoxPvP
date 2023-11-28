package net.kappa.boxpvp.tasks.list;

import depends.xseries.ActionBar;
import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.managers.list.TimerManager;
import net.kappa.boxpvp.utils.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBarTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (TimerManager.isActive(player, "pvp")) {
                if (OptionsFile.actionbar_pvp_enabled) ActionBar.sendActionBar(player, PlaceholderUtil.setPlaceholders(player, OptionsFile.actionbar_pvp));
            } else {
                if (OptionsFile.actionbar_join_enabled) ActionBar.sendActionBar(player, PlaceholderUtil.setPlaceholders(player, OptionsFile.actionbar_join));
            }
        });
    }
}
