package model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Chat {

    private String chatID;
    private Date createdAt;
    private Date lastMessageDate;
    private User lastModifiedBy;
    private Blob media;
    private ArrayList<ChatMessage> pinnedItems;
    private Church church;

    public Chat(String chatID, Church church) {
        this.chatID = chatID;
        this.church = church;
        this.createdAt = Calendar.getInstance().getTime();
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public HashMap<String, User> getMembers() {
        return this.church.getMembers();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getModifiedAt() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
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
