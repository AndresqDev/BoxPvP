package net.kappa.boxpvp.files.list.decoration;

import net.kappa.boxpvp.utils.ColorUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class ScoreboardFile {
    public static List<String> lines = new ArrayList<>();
    public static List<String> animated_title;
    public static List<String> animated_footer;
    public static String static_title;
    public static String static_footer;
    public static boolean footer_status;
    public static boolean anim_title_status;
    public static boolean anim_footer_status;
    public static int board_update_rate;
    public static int title_update_rate;
    public static int footer_update_rate;

    public ScoreboardFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("scoreboard");

        String bar;
        if (!content.getBoolean("scoreboard.options.invisible_bars"))
            bar = ColorUtil.translate(content.getString("scoreboard.options.bars"));
        else bar = " ";

        lines.add(bar);
        lines.addAll(ColorUtil.translate(content.getStringList("scoreboard.lines")));
        lines.add(bar);

        static_title = ColorUtil.translate(content.getString("scoreboard.options.title"));
        static_footer = ColorUtil.translate(content.getString("scoreboard.options.footer"));

        animated_title = ColorUtil.translate(content.getStringList("scoreboard.animated.title"));
        animated_footer = ColorUtil.translate(content.getStringList("scoreboard.animated.footer"));

        footer_status = content.getBoolean("scoreboard.options.footer_enabled");

        anim_title_status = content.getBoolean("scoreboard.options.animated_title");
        anim_footer_status = content.getBoolean("scoreboard.options.animated_footer");

        board_update_rate = content.getInt("scoreboard.options.board_update_rate");
        title_update_rate = content.getInt("scoreboard.animated.title_update_rate");
        footer_update_rate = content.getInt("scoreboard.animated.footer_update_rate");
    }
}
