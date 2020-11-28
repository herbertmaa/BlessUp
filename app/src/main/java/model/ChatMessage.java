package model;

import java.util.Calendar;
import java.util.Date;


/**
 * This class represents the information pertaining to a chat message
 */
public class ChatMessage {
    private String messageID; // Unique message id generated from database
    private String message; // The message
    private User createdBy; // The user ID that created this message
    private Date createdAt; // The date this message was created
    private String messageType; // The type of message

    public ChatMessage() {};

    public ChatMessage(String messageID, String message, User user) {
        this.messageID = messageID;
        this.message = message;
        this.createdBy = user;
        this.createdAt = Calendar.getInstance().getTime();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
