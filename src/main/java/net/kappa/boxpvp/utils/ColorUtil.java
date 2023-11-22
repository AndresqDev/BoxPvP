package net.kappa.boxpvp.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class ColorUtil {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', HexUtil.parseHex(HexUtil.parseGradient(text)));
    }

    public static List<String> translate(List<String> list) {
        return list.stream()
                .map(ColorUtil::translate)
                .collect(Collectors.toList());
    }
}
