package net.kappa.boxpvp.utils.objects;

import java.util.List;

public class MessageObject {
    private List<String> messages;
    private String message;
    private String permission;
    private Integer time;

    public MessageObject(String permission, String message) {
        this.permission = permission;
        this.message = message;
    }

    public MessageObject(List<String> message, Integer time) {
        this.messages = message;
        this.time = time;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPermission() {
        return this.permission;
    }

    public int getTime() {
        return this.time;
    }
}
