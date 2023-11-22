package lib.ziggurat.tab.utils;

import java.beans.ConstructorProperties;
import java.util.UUID;

public class TabEntry {
    private final String id;
    private final UUID uuid;
    private final TabColumn column;
    private final int slot;
    private final int rawSlot;
    private String text;
    private SkinTexture texture;
    private int latency;

    @ConstructorProperties(value = {"id", "uuid", "text", "tab", "texture", "column", "slot", "rawSlot", "latency"})
    public TabEntry(String id, UUID uuid, String text, SkinTexture texture, TabColumn column, int slot, int rawSlot, int latency) {
        this.id = id;
        this.uuid = uuid;
        this.text = text;
        this.texture = texture;
        this.column = column;
        this.slot = slot;
        this.rawSlot = rawSlot;
        this.latency = latency;
    }

    public String getId() {
        return this.id;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SkinTexture getTexture() {
        return this.texture;
    }

    public void setTexture(SkinTexture texture) {
        this.texture = texture;
    }

    public TabColumn getColumn() {
        return this.column;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getRawSlot() {
        return this.rawSlot;
    }

    public int getLatency() {
        return this.latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }
}

