package depends.ziggurat.tab.utils;

import depends.ziggurat.tab.ZigguratCommons;

public class BufferedTabObject {
    private TabColumn column = TabColumn.LEFT;
    private Integer ping;
    private int slot = 1;
    private String text = "";
    private SkinTexture skinTexture = ZigguratCommons.defaultTexture;

    public BufferedTabObject text(String text) {
        this.text = text;
        return this;
    }

    public BufferedTabObject skin(SkinTexture skinTexture) {
        this.skinTexture = skinTexture;
        return this;
    }

    public BufferedTabObject slot(Integer slot) {
        this.slot = slot;
        return this;
    }

    public BufferedTabObject ping(Integer ping) {
        this.ping = ping;
        return this;
    }

    public BufferedTabObject column(TabColumn tabColumn) {
        this.column = tabColumn;
        return this;
    }

    public TabColumn getColumn() {
        return this.column;
    }

    public Integer getPing() {
        return this.ping;
    }

    public int getSlot() {
        return this.slot;
    }

    public String getText() {
        return this.text;
    }

    public SkinTexture getSkinTexture() {
        return this.skinTexture;
    }
}

