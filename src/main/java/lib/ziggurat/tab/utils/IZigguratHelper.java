package lib.ziggurat.tab.utils;

import lib.ziggurat.tab.ZigguratTablistClassic;
import lib.ziggurat.tab.ZigguratTablistModern;

public interface IZigguratHelper {
    TabEntry createFakePlayer(ZigguratTablistModern var1, String var2, TabColumn var3, Integer var4, Integer var5);

    void updateFakeName(ZigguratTablistModern var1, TabEntry var2, String var3);

    void updateFakeLatency(ZigguratTablistModern var1, TabEntry var2, Integer var3);

    void updateFakeSkin(ZigguratTablistModern var1, TabEntry var2, SkinTexture var3);

    void updateHeaderAndFooter(ZigguratTablistModern var1, String var2, String var3);

    void destroyFakePlayer(ZigguratTablistModern var1, TabEntry var2, String var3);

    void cleanup();

    void updateHeaderAndFooter(ZigguratTablistClassic zigguratTablist, String header, String footer);
}

