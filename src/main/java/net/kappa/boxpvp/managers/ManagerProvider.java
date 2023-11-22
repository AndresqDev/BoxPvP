package net.kappa.boxpvp.managers;

import net.kappa.boxpvp.managers.list.FileManager;

import java.util.HashMap;

public class ManagerProvider {
    private FileManager fileManager;

    public ManagerProvider() {
        this.setup();
    }

    private void setup() {
        this.fileManager = new FileManager(new HashMap<String, String>() {{
            put("data", "internal/data.yml");
            put("options", "options.yml");
            put("tab", "decoration/tablist.yml");
            put("scoreboard", "decoration/scoreboard.yml");
        }});
    }

    public void disable() {
        this.fileManager.disable();
    }
}
