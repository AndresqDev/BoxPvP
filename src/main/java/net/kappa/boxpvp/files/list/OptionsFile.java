package net.kappa.boxpvp.files.list;

import net.kappa.boxpvp.utils.ColorUtil;
import net.kappa.boxpvp.utils.EntryUtil;
import net.kappa.boxpvp.utils.objects.MessageObject;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class OptionsFile {
    // - Blocking Options
    public static List<String> blocked_words;
    public static List<String> blocked_commands;
    public static String blocked_word_msg;
    public static String blocked_commands_msg;

    // - SocialLinks Options
    public static String social_store;
    public static String social_discord;
    public static String social_twitter;
    public static String social_ts;

    // - World Options
    public static boolean world_block_break;
    public static boolean world_block_place;
    public static boolean world_weather_changes;
    public static boolean world_hunger_loss;
    public static boolean world_mob_spawn;
    public static boolean world_entity_damage;
    public static boolean world_item_drop;
    public static boolean world_item_pickup;

    // - Messages Options
    public static List<MessageObject> message_timed_objects = new ArrayList<>();
    public static List<MessageObject> message_global_join_format = new ArrayList<>();
    public static List<String> message_join_format;
    public static String message_send_format;
    public static String message_receive_format;

    // - Other Options
    public static String others_chatformat_placeholder;
    public static String others_actionbar_placeholder;
    public static int others_clearlag_delay;

    public OptionsFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("options");

        //Blocking options
        blocked_words = content.getStringList("options.blocked_words");
        blocked_commands = content.getStringList("options.blocked_commands");
        blocked_word_msg = ColorUtil.translate(content.getString("options.blocked_word_msg"));
        blocked_commands_msg = ColorUtil.translate(content.getString("options.blocked_commands_msg"));

        //SocialLinks Options
        social_store = ColorUtil.translate(content.getString("options.social_links.store"));
        social_discord = ColorUtil.translate(content.getString("options.social_links.discord"));
        social_twitter = ColorUtil.translate(content.getString("options.social_links.twitter"));
        social_ts = ColorUtil.translate(content.getString("options.social_links.ts"));

        //World Options
        world_block_break = content.getBoolean("options.world.block_break");
        world_block_place = content.getBoolean("options.world.block_place");
        world_weather_changes = content.getBoolean("options.world.weather_changes");
        world_hunger_loss = content.getBoolean("options.world.hunger_loss");
        world_mob_spawn = content.getBoolean("options.world.mob_spawn");
        world_entity_damage = content.getBoolean("options.world.entity_damage");
        world_item_drop = content.getBoolean("options.world.item_drop");
        world_item_pickup = content.getBoolean("options.world.item_pickup");

        //Messages Options
        Objects.requireNonNull(content.getConfigurationSection("options.automatic_messages")).getKeys(false).forEach(entryName -> message_timed_objects.add(new MessageObject(ColorUtil.translate(content.getStringList(EntryUtil.getOf("options.automatic_messages." + entryName, "message"))), content.getInt(EntryUtil.getOf("options.automatic_messages." + entryName, "time")))));
        Objects.requireNonNull(content.getConfigurationSection("options.global_join_message")).getKeys(false).forEach(entryName -> message_global_join_format.add(new MessageObject(content.getString(EntryUtil.getOf("options.global_join_message." + entryName, "permission")), ColorUtil.translate(content.getString(EntryUtil.getOf("options.global_join_message." + entryName, "message"))))));
        message_join_format = ColorUtil.translate(content.getStringList("options.default_join_message"));
        message_send_format = ColorUtil.translate(content.getString("options.private_messages.send_format"));
        message_receive_format = ColorUtil.translate(content.getString("options.private_messages.receive_format"));

        //Other Options
        others_chatformat_placeholder = ColorUtil.translate(content.getString("options.chat_format"));
        others_actionbar_placeholder = ColorUtil.translate(content.getString("options.actionbar"));
        others_clearlag_delay = content.getInt("options.clearlag_delay");
    }
}
