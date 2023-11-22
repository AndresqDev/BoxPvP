package lib.ziggurat.utils.playerversion;

import java.util.Arrays;

public enum PlayerVersion {
    v1_7(4, 5),
    v1_8(47),
    v1_9(107, 108, 109, 110),
    v1_10(210),
    v1_11(315, 316),
    v1_12(335, 338, 340),
    v1_13(393, 401, 404),
    v1_14(477, 480, 485),
    v1_15(573, 575, 578),
    v1_16(735, 736, 751, 753, 754),
    v1_17(755, 756),
    v1_18(757, 758),
    v1_19(759, 760, 761, 762);

    private Integer[] rawVersion;

    PlayerVersion(Integer... rawVersionNumbers) {
        this.setup(rawVersionNumbers);
    }

    public static PlayerVersion getVersionFromRaw(Integer input) {
        for (PlayerVersion playerVersion : PlayerVersion.values()) {
            if (!Arrays.asList(playerVersion.rawVersion).contains(input)) continue;
            return playerVersion;
        }
        return v1_8;
    }

    private void setup(Integer... rawVersionNumbers) {
        this.rawVersion = rawVersionNumbers;
    }

}

