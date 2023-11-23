package depends.ziggurat.utils;

import depends.ziggurat.tab.utils.BufferedTabObject;
import depends.ziggurat.tab.utils.SkinTexture;
import depends.ziggurat.tab.utils.TabColumn;

public class ObjectUtility {
    public static BufferedTabObject createTabEntry(String text, TabColumn column, int slot, int ping, SkinTexture skin) {
        return new BufferedTabObject()
                .text(text)
                .column(column)
                .slot(slot)
                .ping(ping)
                .skin(skin);
    }
}
