package net.kappa.boxpvp.utils;

import net.kappa.boxpvp.utils.presets.PatternsPreset;
import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.regex.Matcher;

import static net.kappa.boxpvp.Main.pseudoProtocol;

public class HexUtil {
    public static String parseHex(String hex) {
        if (pseudoProtocol < 16) return hex;
        final Matcher matcher = PatternsPreset.hex.matcher(hex);
        if (matcher.find()) {
            final String color = matcher.group(1);
            final ChatColor chatColor = ChatColor.of("#" + color);
            final String before = hex.substring(0, matcher.start());
            final String after = hex.substring(matcher.end());
            hex = before + chatColor + after;
            return parseHex(hex);
        }
        return hex;
    }

    public static String parseGradient(String gradient) {
        if (pseudoProtocol < 16) return gradient;
        final Matcher matcher = PatternsPreset.gradient.matcher(gradient);
        if (matcher.find()) {
            final String startHexColor = matcher.group(1);
            final String endHexColor = matcher.group(3);
            final Color startColor = Color.decode("#" + startHexColor);
            final Color endColor = Color.decode("#" + endHexColor);
            final String parsedGradient = parseGradient(matcher.group(2), startColor, endColor);
            return parseGradient(gradient.substring(0, matcher.start()) + parsedGradient + gradient.substring(matcher.end()));
        }
        return gradient;
    }

    public static String parseGradient(String text, Color start, Color end) {
        final int textLength = text.length();
        final StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < textLength; i++) {
            float ratio = (float) i / (float) textLength;
            int red = (int) (start.getRed() * (1 - ratio) + end.getRed() * ratio);
            int green = (int) (start.getGreen() * (1 - ratio) + end.getGreen() * ratio);
            int blue = (int) (start.getBlue() * (1 - ratio) + end.getBlue() * ratio);
            final Color stepColor = new Color(red, green, blue);
            final String hexColor = String.format("#%02x%02x%02x", stepColor.getRed(), stepColor.getGreen(), stepColor.getBlue());
            finalText.append(ChatColor.of(hexColor)).append(text.charAt(i));
        }
        return finalText.toString();
    }
}
