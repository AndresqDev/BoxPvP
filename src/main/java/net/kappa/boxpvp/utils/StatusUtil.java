package net.kappa.boxpvp.utils;

public class StatusUtil {
    private static boolean placeholderAPI = false;

    public static boolean getPlaceholder() {
        return !placeholderAPI;
    }

    public static void setPlaceholder(boolean status) {
        placeholderAPI = status;
    }
}
