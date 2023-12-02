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
    // - Blocking
    public static List<String> blocked_words;
    public static List<String> blocked_commands;
    public static String blocked_word_msg;
    public static String blocked_commands_msg;

    // - World
    public static boolean world_block_break;
    public static boolean world_block_place;
    public static boolean world_weather_changes;
    public static boolean world_hunger_loss;
    public static boolean world_mob_spawn;
    public static boolean world_entity_damage;
    public static boolean world_item_drop;
    public static boolean world_item_pickup;

    // - Messages
    public static List<MessageObject> message_timed_objects = new ArrayList<>();
    public static List<MessageObject> message_global_join_format = new ArrayList<>();

    // - ActionBar
    public static String actionbar_join;
    public static Boolean actionbar_join_enabled;
    public static String actionbar_pvp;
    public static Boolean actionbar_pvp_enabled;

    // - CombatLog
    public static String combatlog_name;
    public static Boolean combatlog_enabled;

    // - Other Options
    public static String others_chatformat_placeholder;
    public static int others_clearlag_delay;

    public OptionsFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("options");

        //Blocking
        blocked_words = content.getStringList("options.chat.blocked_words");
        blocked_commands = content.getStringList("options.chat.blocked_commands");
        blocked_word_msg = ColorUtil.translate(content.getString("options.chat.blocked_word_msg"));
        blocked_commands_msg = ColorUtil.translate(content.getString("options.chat.blocked_commands_msg"));

        //World
        world_block_break = content.getBoolean("options.world.block_break");
        world_block_place = content.getBoolean("options.world.block_place");
        world_weather_changes = content.getBoolean("options.world.weather_changes");
        world_hunger_loss = content.getBoolean("options.world.hunger_loss");
        world_mob_spawn = content.getBoolean("options.world.mob_spawn");
        world_entity_damage = content.getBoolean("options.world.entity_damage");
        world_item_drop = content.getBoolean("options.world.item_drop");
        world_item_pickup = content.getBoolean("options.world.item_pickup");

        //Messages
        Objects.requireNonNull(content.getConfigurationSection("options.chat.automatic_messages")).getKeys(false).forEach(entryName -> message_timed_objects.add(new MessageObject(ColorUtil.translate(content.getStringList(EntryUtil.getOf("options.chat.automatic_messages." + entryName, "message"))), content.getInt(EntryUtil.getOf("options.chat.automatic_messages." + entryName, "time")))));
        Objects.requireNonNull(content.getConfigurationSection("options.chat.global_join_message")).getKeys(false).forEach(entryName -> message_global_join_format.add(new MessageObject(content.getString(EntryUtil.getOf("options.chat.global_join_message." + entryName, "permission")), ColorUtil.translate(content.getString(EntryUtil.getOf("options.chat.global_join_message." + entryName, "message"))))));

        //Actionbar
        actionbar_join = ColorUtil.translate(content.getString("options.actionbar.join"));
        actionbar_join_enabled = content.getBoolean("options.actionbar.join.enabled");
        actionbar_pvp = ColorUtil.translate(content.getString("options.actionbar.pvp"));
        actionbar_pvp_enabled = content.getBoolean("options.actionbar.pvp.enabled");

        //CombatLog
        combatlog_name = ColorUtil.translate(content.getString("options.combatlog.entity.name"));
        combatlog_enabled = content.getBoolean("options.combatlog.entity.enabled");

        //Other
        others_chatformat_placeholder = ColorUtil.translate(content.getString("options.chat.chat_format"));
        others_clearlag_delay = content.getInt("options.lag.clearlag_delay");
    }
}
