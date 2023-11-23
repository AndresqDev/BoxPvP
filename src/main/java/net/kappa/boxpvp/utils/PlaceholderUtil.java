package net.kappa.boxpvp.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kappa.boxpvp.files.list.messages.AdminFile;
import net.kappa.boxpvp.managers.list.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlaceholderUtil {
    public static String setPlaceholders(Player player, String str) {
        return applyExternals(player, str.replace("%player%", player.getName())
                .replace("%displayname%", player.getDisplayName())
                .replace("%player_live%", String.valueOf(player.getHealthScale()))
                .replace("%player_kills%", String.valueOf(player.getStatistic(Statistic.PLAYER_KILLS)))
                .replace("%online_players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%rank%", ColorUtil.translate(RankManager.getImpl().getGroupPrefix(player)).replace("[", "").replace("]", ""))
                .replace("%date%", LocalDate.now().toString()))
                .replace("%server_prefix_admin%", AdminFile.pluginPrefix)
                .replace("%server_prefix%", AdminFile.pluginPrefix);
    }

    public static List<String> setPlaceholders(Player player, List<String> strs) {
        return applyExternals(player, strs.stream()
                .map(str -> str.replace("%player%", player.getName())
                        .replace("%displayname%", player.getDisplayName())
                        .replace("%player_live%", String.valueOf(player.getHealthScale()))
                        .replace("%player_kills%", String.valueOf(player.getStatistic(Statistic.PLAYER_KILLS)))
                        .replace("%online_players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                        .replace("%rank%", Objects.requireNonNull(ColorUtil.translate(RankManager.getImpl().getGroupPrefix(player)))).replace("[", "").replace("]", "")
                        .replace("%date%", LocalDate.now().toString().replace("-", ", ")))
                .collect(Collectors.toList()));
    }

    private static String applyExternals(Player player, String str) {
        if (StatusUtil.getPlaceholder()) return str;

        return PlaceholderAPI.setPlaceholders(player, str);
    }

    private static List<String> applyExternals(Player player, List<String> strs) {
        if (StatusUtil.getPlaceholder()) return strs;

        return PlaceholderAPI.setPlaceholders(player, strs);
    }
}
