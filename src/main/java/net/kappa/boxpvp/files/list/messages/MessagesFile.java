package net.kappa.boxpvp.files.list.messages;

import net.kappa.boxpvp.utils.ColorUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Objects;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class MessagesFile {

    // General
    public static String pluginPrefix;

    // SocialLinks Options
    public static String social_store;
    public static String social_discord;
    public static String social_twitter;
    public static String social_ts;

    // Private Message
    public static String private_message_send_format;
    public static String private_message_receive_format;

    // Join Messages
    public static List<String> message_join_format;



    public MessagesFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("messages");

        // var = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.")));

        // General
        pluginPrefix = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.plugin-prefix")));

        // SocialLinks Messages
        social_store = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.social_links.store")));
        social_discord = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.social_links.discord")));
        social_twitter = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.social_links.twitter")));
        social_ts = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.social_links.ts")));

        // Private Messages
        private_message_send_format = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.private_messages.send_format")));
        private_message_receive_format = ColorUtil.translate(Objects.requireNonNull(content.getString("messages.private_messages.receive_format")));

        // Join Messages
        message_join_format = ColorUtil.translate(content.getStringList("options.default_join_message"));

    }
}
