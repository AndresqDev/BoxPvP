package net.kappa.boxpvp.files.list.decoration;

import depends.ziggurat.tab.utils.TabColumn;
import depends.ziggurat.tab.utils.TabObject;
import net.kappa.boxpvp.utils.ColorUtil;
import net.kappa.boxpvp.utils.EntryUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class TabFile {
    public static String header;
    public static String footer;
    public static String tab_type;
    public static String classic_format;
    public static List<TabObject> heads = new ArrayList<>();
    public static List<TabObject> left = new ArrayList<>();
    public static List<TabObject> middle = new ArrayList<>();
    public static List<TabObject> right = new ArrayList<>();
    public static List<TabObject> far_right = new ArrayList<>();
    public static int tab_update_rate;

    public TabFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("tab");

        header = ColorUtil.translate(content.getStringList("tablist.options.header")).stream()
                .map(array -> String.join(" ", array))
                .collect(Collectors.joining("\n"));
        footer = ColorUtil.translate(content.getStringList("tablist.options.footer")).stream()
                .map(array -> String.join(" ", array))
                .collect(Collectors.joining("\n"));

        tab_type = Objects.requireNonNull(content.getString("tablist.options.tab_type")).toLowerCase();
        classic_format = ColorUtil.translate(content.getString("tablist.options.tab_classic_format"));

        if (tab_type.equals("modern")) {
            Objects.requireNonNull(content.getConfigurationSection("tablist.custom_heads")).getKeys(false).forEach(entryName -> heads.add(new TabObject(content.getString(EntryUtil.getOf("tablist.custom_heads." + entryName, "placeholder")), content.getString(EntryUtil.getOf("tablist.custom_heads." + entryName, "value")), content.getString(EntryUtil.getOf("tablist.custom_heads." + entryName, "signature")))));
            Objects.requireNonNull(content.getConfigurationSection("tablist.columns.LEFT")).getKeys(false).forEach(entryName -> left.add(new TabObject(TabColumn.LEFT, ColorUtil.translate(content.getString(EntryUtil.getOf("tablist.columns.LEFT." + entryName, "text"))), content.getString(EntryUtil.getOf("tablist.columns.LEFT." + entryName, "head")), content.getInt(EntryUtil.getOf("tablist.columns.LEFT." + entryName, "slot")), content.getInt(EntryUtil.getOf("tablist.columns.LEFT." + entryName, "ping")))));
            Objects.requireNonNull(content.getConfigurationSection("tablist.columns.MIDDLE")).getKeys(false).forEach(entryName -> middle.add(new TabObject(TabColumn.MIDDLE, ColorUtil.translate(content.getString(EntryUtil.getOf("tablist.columns.MIDDLE." + entryName, "text"))), content.getString(EntryUtil.getOf("tablist.columns.MIDDLE." + entryName, "head")), content.getInt(EntryUtil.getOf("tablist.columns.MIDDLE." + entryName, "slot")), content.getInt(EntryUtil.getOf("tablist.columns.MIDDLE." + entryName, "ping")))));
            Objects.requireNonNull(content.getConfigurationSection("tablist.columns.RIGHT")).getKeys(false).forEach(entryName -> right.add(new TabObject(TabColumn.RIGHT, ColorUtil.translate(content.getString(EntryUtil.getOf("tablist.columns.RIGHT." + entryName, "text"))), content.getString(EntryUtil.getOf("tablist.columns.RIGHT." + entryName, "head")), content.getInt(EntryUtil.getOf("tablist.columns.RIGHT." + entryName, "slot")), content.getInt(EntryUtil.getOf("tablist.columns.RIGHT." + entryName, "ping")))));
            Objects.requireNonNull(content.getConfigurationSection("tablist.columns.FAR_RIGHT")).getKeys(false).forEach(entryName -> far_right.add(new TabObject(TabColumn.FAR_RIGHT, ColorUtil.translate(content.getString(EntryUtil.getOf("tablist.columns.FAR_RIGHT." + entryName, "text"))), content.getString(EntryUtil.getOf("tablist.columns.FAR_RIGHT." + entryName, "head")), content.getInt(EntryUtil.getOf("tablist.columns.FAR_RIGHT." + entryName, "slot")), content.getInt(EntryUtil.getOf("tablist.columns.FAR_RIGHT." + entryName, "ping")))));
        }

        tab_update_rate = content.getInt("tablist.options.tab_update_rate");
    }
}
