package depends.ziggurat.tab.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.URL;

public class SkinTexture {
    public String SKIN_VALUE;
    public String SKIN_SIGNATURE;

    public SkinTexture(String skinValue, String skinSig) {
        this.SKIN_VALUE = skinValue;
        this.SKIN_SIGNATURE = skinSig;
    }

    public static String[] getSkin(String player) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + player);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();
            URL sessionUrl = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader sessionReader = new InputStreamReader(sessionUrl.openStream());
            JsonObject textureProperty = new JsonParser().parse(sessionReader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String value = textureProperty.get("value").getAsString();
            String signature = textureProperty.get("signature").getAsString();
            return new String[]{value, signature};
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]{"", ""};
    }

    public String toString() {
        return this.SKIN_SIGNATURE + this.SKIN_VALUE;
    }
}

