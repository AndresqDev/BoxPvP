package net.kappa.boxpvp.files.list.messages;

import net.kappa.boxpvp.utils.ColorUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class MessagesFile {
    public static String pluginPrefix;

    public MessagesFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("messages");

        pluginPrefix = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.plugin-prefix")));

    }
}
