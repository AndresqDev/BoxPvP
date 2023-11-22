package lib.ziggurat.tab.utils;

import lib.ziggurat.tab.ZigguratCommons;
import net.kappa.boxpvp.files.list.decoration.TabFile;

public class TabObject {
    private final String text;
    private TabColumn column;
    private String placeholder;
    private SkinTexture head;
    private int slot;
    private int ping;
    private String value;
    private String signature;

    public TabObject(TabColumn column, String text, String placeholder, int slot, int ping) {
        this.column = column;
        this.text = text;
        this.placeholder = placeholder;
        this.slot = slot;
        this.ping = ping;
        TabFile.heads.forEach(s -> {
            this.value = s.getText().equals(this.placeholder) ? s.getValue() : ZigguratCommons.defaultTexture.SKIN_VALUE;
            this.signature = s.getText().equals(this.placeholder) ? s.getSignature() : ZigguratCommons.defaultTexture.SKIN_SIGNATURE;
            this.head = new SkinTexture(this.value, this.signature);
        });
    }

    public TabObject(String text, String value, String signature) {
        this.text = text;
        this.value = value;
        this.signature = signature;
    }

    public TabColumn getColumn() {
        return this.column;
    }

    public String getText() {
        return this.text;
    }

    public SkinTexture getHead() {
        return this.head;
    }

    public int getSlot() {
        return this.slot;
    }

    public int getPing() {
        return this.ping;
    }

    public String getValue() {
        return this.value;
    }

    public String getSignature() {
        return this.signature;
    }
}
