package net.kappa.boxpvp.tasks.list;

import depends.xseries.ActionBar;
import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.utils.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBarTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p -> ActionBar.sendActionBar(p, PlaceholderUtil.setPlaceholders(p, OptionsFile.others_actionbar_placeholder)));
    }
}
