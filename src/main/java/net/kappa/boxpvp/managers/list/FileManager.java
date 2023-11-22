package net.kappa.boxpvp.managers.list;

import net.kappa.boxpvp.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.kappa.boxpvp.Main.plugin;

public class FileManager {
    public static Map<String, FileConfiguration> fileconfig = new HashMap<>();
    public static Map<String, File> filedata = new HashMap<>();

    public FileManager(Map<String, String> f) {
        try {
            this.registerFiles(f);
        } catch (IOException exc) {
            plugin.getLogger().info("Error loading file system.");
        }
    }

    @SuppressWarnings("all")
    private void registerFiles(Map<String, String> f) throws IOException {
        for (Map.Entry<String, String> file : f.entrySet()) {
            final File data = new File(Main.file, file.getValue());
            if (!data.exists()) {
                if (!data.getParentFile().exists()) {
                    data.getParentFile().mkdirs();
                }
                plugin.saveResource(file.getValue(), false);
            }
            fileconfig.put(file.getKey(), YamlConfiguration.loadConfiguration(data));
            filedata.put(file.getKey(), data);
        }
    }

    public void disable() {
        fileconfig.clear();
        filedata.clear();
    }
}
