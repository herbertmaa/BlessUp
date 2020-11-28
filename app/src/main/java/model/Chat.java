package model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A Chat object representing the Group Chat that belongs to a Church's Chat Channel
 */
public class Chat {

    /**
     * ChatID used to query Firebase for this Object
     */
    private String chatID;

    /**
     * Date this chat channel was created
     */
    private Date createdAt;

    /**
     * The Data of the last message sent in this chat
     */
    private Date lastMessageDate;

    /**
     * User that sent the last message
     */
    private User lastModifiedBy;

    /**
     * Object to hold gifs & images that are sent in the Chat
     */
    private Blob media;

    /**
     * Pinned Items in the chat
     */
    private ArrayList<ChatMessage> pinnedItems;

    /**
     * The Church this group chat is linked to
     */
    private Church church;

    /**
     *
     * @param chatID
     * @param church
     */
    public Chat(String chatID, Church church) {
        this.chatID = chatID;
        this.church = church;
        this.createdAt = Calendar.getInstance().getTime();
    }

    /**
     * @return - ChatID used to query Firebase for this Object
     */
    public String getChatID() {
        return chatID;
    }

    /**
     * @param chatID - ChatID used to query Firebase for this Object
     */
    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    /**
     * @return - a list of members that belong to this group chat
     */
    public HashMap<String, User> getMembers() {
        return this.church.getMembers();
    }

    /**
     * @return - the date this group chat was created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt - the date this group chat was created
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return - the user that last sent a message in this group chat
     */
    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy - the user that last sent a message in this group chat
     */
    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return the Date at which the last message was sent
     */
    public Date getModifiedAt() {
        return lastMessageDate;
    }

    /**
     * @param lastMessageDate the Data at which the last message was sent
     */
    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    /**
     * @return media items for this chat
     */
    public Blob getMedia() {
        return media;
    }

    /**
     * @param media - sets Media Items for this Chat
     */
    public void setMedia(Blob media) {
        this.media = media;
    }

    /**
     * @return - ArrayList of ChatMessages that have been pinned to the Chat
     */
    public ArrayList<ChatMessage> getPinnedItems() {
        return pinnedItems;
    }

    /**
     * @param pinnedItems - Sets the pinned items to @param pinnedItems
     */
    public void setPinnedItems(ArrayList<ChatMessage> pinnedItems) {
        this.pinnedItems = pinnedItems;
    }
}
