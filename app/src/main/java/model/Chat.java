package model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;

public class Chat {

    private String chatID;
    private ArrayList<User> members;
    private ArrayList<Church> churches;
    private User createdBy;
    private Calendar createdAt;
    private User modifiedBy;
    private Calendar modifiedAt;
    private Blob media;
    private ArrayList<ChatMessage> pinnedItems;

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public ArrayList<Church> getChurches() {
        return churches;
    }

    public void setChurches(ArrayList<Church> churches) {
        this.churches = churches;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Calendar getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Calendar modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Blob getMedia() {
        return media;
    }

    public void setMedia(Blob media) {
        this.media = media;
    }

    public ArrayList<ChatMessage> getPinnedItems() {
        return pinnedItems;
    }

    public void setPinnedItems(ArrayList<ChatMessage> pinnedItems) {
        this.pinnedItems = pinnedItems;
    }
}
