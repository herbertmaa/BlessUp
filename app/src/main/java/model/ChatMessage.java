package model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Calendar;
import java.util.Date;

public class ChatMessage {
    private String messageID;
    private String message;
    private User createdBy;
    private Date createdAt;
    private String messageType;

    public ChatMessage() {};

    public ChatMessage(String messageID, String message, User user) {
        this.messageID = messageID;
        this.message = message;
        this.createdBy = user;
        this.createdAt = getDate();
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

    public boolean isBelongsToCurrentUser() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        firebaseUser.getUid();
        System.out.print("BELONGS TO CURRENT USER: " + createdBy.getId() == firebaseUser.getUid());
        return createdBy.getId() == firebaseUser.getUid();
    }

    private Date getDate() {
       return Calendar.getInstance().getTime();
    }
}
