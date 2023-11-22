package net.kappa.boxpvp.utils.presets;

import java.util.regex.Pattern;

public class PatternsPreset {
    public static Pattern gradient = Pattern.compile("<gradient:([0-9a-fA-F]{6})>(.+?)<!gradient:([0-9a-fA-F]{6})>");
    public static Pattern hex = Pattern.compile("<hex:([A-Fa-f0-9]{6})>");
}
