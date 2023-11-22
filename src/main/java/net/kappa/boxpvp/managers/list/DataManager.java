package net.kappa.boxpvp.managers.list;

import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.tasks.TaskProvider;
import net.kappa.boxpvp.tasks.list.MineRegenTask;
import net.kappa.boxpvp.utils.Cuboid;
import net.kappa.boxpvp.utils.LocationUtil;
import net.kappa.boxpvp.utils.objects.MineObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.kappa.boxpvp.Main.plugin;
import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;
import static net.kappa.boxpvp.managers.list.FileManager.filedata;

public class DataManager {
    public static void updateSpawn(Location location) {
        final FileConfiguration content = fileconfig.get("data");
        DataFile.spawn_location = location;
        content.set("spawn_location.data", LocationUtil.toString(location));
        try {
            content.save(filedata.get("data"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void addMine(String name, Cuboid cuboid, List<Material> types, int regen) {
        final FileConfiguration content = fileconfig.get("data");
        final List<String> materials = new ArrayList<>();

        content.set("mines."+name+".cuboid", cuboid.toString());
        content.set("mines."+name+".regen", regen);
        types.forEach(t -> {
            materials.add(t.name());
            content.set("mines."+name+".types", t.name());
        });

        final MineObject m = new MineObject(name, cuboid, regen, materials);

        DataFile.mines.add(m);

        final MineRegenTask regenTask = new MineRegenTask(m);
        regenTask.runTaskTimer(plugin, 20L, m.getTime());
        plugin.taskProvider.addTask(regenTask.getTaskId());

        try {
            content.save(filedata.get("data"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void removeMine(String name) {
        final FileConfiguration content = fileconfig.get("data");

        DataFile.mines.forEach(m -> {
            if (m.getName().equals(name)) DataFile.mines.remove(m);
        });
        content.set("mines."+name, null);

        try {
            content.save(filedata.get("data"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
