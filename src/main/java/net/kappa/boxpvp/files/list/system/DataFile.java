package net.kappa.boxpvp.files.list.system;

import net.kappa.boxpvp.utils.Cuboid;
import net.kappa.boxpvp.utils.LocationUtil;
import net.kappa.boxpvp.utils.objects.MineObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class DataFile {
    public static List<MineObject> mines = new ArrayList<>();
    public static Location spawn_location;
    public static Location alternative_spawn;

    public DataFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("data");
        final ConfigurationSection section = content.getConfigurationSection("mines");

        if(section != null) {
            section.getKeys(false).forEach(key -> {
                List<String> types = new ArrayList<>(content.getStringList("mines." + key + ".types"));

                if (types.isEmpty()) types.add(content.getString("mines." + key + ".types"));

                mines.add(new MineObject(
                        key,
                        new Cuboid(
                                Objects.requireNonNull(content.getString("mines." + key + ".cuboid")))
                        , content.getInt("mines." + key + ".regen")
                        , types));
            });
        }

        if (content.getConfigurationSection("spawn_location") == null) {
            spawn_location = null;
            alternative_spawn = new Location(Bukkit.getWorlds().get(0), 0, 75, 0, 0, 0);
        } else {
            spawn_location = LocationUtil.toLocation(Objects.requireNonNull(content.getString("spawn_location.data")));
        }
    }
}
