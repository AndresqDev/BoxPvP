package depends.ziggurat.utils;

import depends.ziggurat.utils.playerversion.PlayerVersionHandler;

public class UtilityManager {
    private static boolean initiated = false;

    public static void init() {
        if (initiated) {
            return;
        }
        new PlayerVersionHandler();
        initiated = true;
    }
}

