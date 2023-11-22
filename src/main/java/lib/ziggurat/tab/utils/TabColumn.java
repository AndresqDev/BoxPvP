package lib.ziggurat.tab.utils;

import lib.ziggurat.utils.MathsUtility;
import lib.ziggurat.utils.PlayerUtility;
import lib.ziggurat.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TabColumn {
    LEFT(-2, 1, 3),
    MIDDLE(-1, 21, 3),
    RIGHT(0, 41, 3),
    FAR_RIGHT(60, 61, 1);

    private static final TabColumn[] cachedValues;

    static {
        cachedValues = TabColumn.values();
    }

    private final int startNumber;
    private final int incrementBy;
    private final int rawStart;
    private final List<Integer> numbers = new ArrayList<>();

    TabColumn(int rawStart, int startNumber, int incrementBy) {
        this.rawStart = rawStart;
        this.startNumber = startNumber;
        this.incrementBy = incrementBy;
        this.generate();
    }

    @SuppressWarnings("all")
    public static TabColumn getFromSlot(Player player, Integer slot) {
        if (PlayerUtility.getPlayerVersion(player) == PlayerVersion.v1_7) {
            return Arrays.stream(cachedValues).filter(tabColumn -> tabColumn.getNumbers().contains(slot)).findFirst().get();
        }
        if (MathsUtility.isBetween(slot, 1, 20)) {
            return LEFT;
        }
        if (MathsUtility.isBetween(slot, 21, 40)) {
            return MIDDLE;
        }
        if (MathsUtility.isBetween(slot, 41, 60)) {
            return RIGHT;
        }
        if (MathsUtility.isBetween(slot, 61, 80)) {
            return FAR_RIGHT;
        }
        return null;
    }

    private void generate() {
        for (int i = 1; i <= 20; ++i) {
            Integer numb = this.rawStart + i * this.incrementBy;
            this.numbers.add(numb);
        }
    }

    public Integer getNumb(Player player, int raw) {
        if (PlayerUtility.getPlayerVersion(player) != PlayerVersion.v1_7) {
            return raw - this.startNumber + 1;
        }
        int number = 0;
        for (int integer : this.numbers) {
            ++number;
            if (integer != raw) continue;
            return number;
        }
        return number;
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }
}

