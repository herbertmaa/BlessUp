package model;

import java.util.Calendar;

public class ChatMessage {
    private String messageID;
    private String message;
    private User createdBy;
    private String createdAt;
    private String messageType;

    public ChatMessage() {};

    public ChatMessage(String messageID, String message, String createdAt) {
        this.messageID = messageID;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
