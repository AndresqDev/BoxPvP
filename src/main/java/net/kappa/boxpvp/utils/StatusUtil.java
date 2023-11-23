package net.kappa.boxpvp.utils;

import static net.kappa.boxpvp.Main.pluginManager;

public class StatusUtil {
    private static boolean placeholderAPI = false;

    public static boolean getPlaceholder() {
        return !placeholderAPI;
    }

    public static void setPlaceholder(boolean status) {
        placeholderAPI = status;
    }

    public static boolean isEnabled(String plugin) {
        return pluginManager.isPluginEnabled(plugin);
    }
}
