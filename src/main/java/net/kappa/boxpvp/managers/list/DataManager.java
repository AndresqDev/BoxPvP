package net.kappa.boxpvp.managers.list;

import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

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
}
