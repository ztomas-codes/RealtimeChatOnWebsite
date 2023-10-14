package ztomas.me.chatwebmoderating.Objects;

import java.time.LocalDateTime;

public class Message {


    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public String getMessage() {
        return message;
    }

    private LocalDateTime messageTime;
    private String message;

    public Message(LocalDateTime messageTime, String message) {
        this.messageTime = messageTime;
        this.message = message;
    }
}
