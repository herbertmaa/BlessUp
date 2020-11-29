package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Chat Message that stores details about the user who owns the message,
 *  what string was sent, the time the message was created, and if the message
 *  was an image, or text file.
 */
public class ChatMessage {
    /**
     * The ID used to query for this message object in Firebase
     */
    private String messageID;

    /**
     * The string that was sent to the Chat Channel
     */
    private String message;

    /**
     * The user who sent the message
     */
    private User createdBy;

    /**
     * The time the message was sent
     */
    private Date createdAt;

    /**
     * The format of the message
     */
    private String messageType;


    /**
     * No Param Constructor
     */
    public ChatMessage() {};

    public ChatMessage(String messageID, String message, User user) {
        this.messageID = messageID;
        this.message = message;
        this.createdBy = user;
        this.createdAt = Calendar.getInstance().getTime();
    }

    /**
     * @return The ID used to query for this message object in Firebase
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * @param messageID - The ID used to query for this message object in Firebase
     */
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    /**
     *
     * @return - The string that was sent to the Chat Channel
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message - The string that was sent to the Chat Channel
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return - The user who sent the message
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy - The user who sent the message
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return - The time the message was sent
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt - The time the message was sent
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return - The format of the message (Image or String)
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType - The format of the message (Image or String)
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
