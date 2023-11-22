package net.kappa.boxpvp.files.list.system;

import net.kappa.boxpvp.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class DataFile {
    public static Location spawn_location;
    public static Location alternative_spawn;

    public DataFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("data");
        if (content.getConfigurationSection("spawn_location") == null) {
            spawn_location = null;
            alternative_spawn = new Location(Bukkit.getWorlds().get(0), 0, 75, 0, 0, 0);
        } else {
            spawn_location = LocationUtil.toLocation(Objects.requireNonNull(content.getString("spawn_location.data")));
        }
    }
}
