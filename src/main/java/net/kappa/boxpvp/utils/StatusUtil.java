package net.kappa.boxpvp.utils;

public class StatusUtil {
    private static boolean protocolLib = false;

    public static boolean getProtocol() {
        return !protocolLib;
    }

    public static void setProtocol(boolean status) {
        protocolLib = status;
    }
}
