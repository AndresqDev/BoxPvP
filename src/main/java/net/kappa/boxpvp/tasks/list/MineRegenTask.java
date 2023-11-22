package net.kappa.boxpvp.tasks.list;

import net.kappa.boxpvp.utils.objects.MineObject;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class MineRegenTask extends BukkitRunnable {
    private final MineObject mine;

    public MineRegenTask(MineObject mine){
        this.mine = mine;
    }

    @Override
    public void run() {
        final Random random = new Random();

        mine.getCuboid().forEach(b -> {
            final Material material = mine.getTypes().get(random.nextInt(mine.getTypes().size()));
            b.setType(material);
        });
    }
}
