package lib.ziggurat.tab;

import lib.ziggurat.tab.utils.BufferedTabObject;
import org.bukkit.entity.Player;

import java.util.Set;

public interface ZigguratAdapter {
    Set<BufferedTabObject> getSlots(Player var1);

    String getClassicSlot(Player var1);

    String getFooter();

    String getHeader();
}

