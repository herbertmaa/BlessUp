package model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;

public class Church implements Serializable {

    /**
     * The ID used to Query this Church object from Firebase
     */
    private String churchID;

    /**
     * The name of this church
     */
    private String name;

    /**
     * This church's address
     */
    private String address;

    /**
     * This church's phone number
     */
    private String phoneNumber;

    /**
     * This church's description
     */
    private String description;

    /**
     * The image url that contains a photo of this church
     */
    private String imageURL;
    public HashMap<String, User> members;

    /**
     * Gets the image's URL in FirebaseStore
     * @return
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Default Constructor
     */
    private Church() {}

    public Church(String churchID, String name, String address, String phoneNumber, String religion, String description) {
        this.churchID = churchID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.members = new HashMap<>();
        this.imageURL = null;
    }

    /**
     *
     * @return - The ID used to Query this Church object from Firebase
     */
    public String getChurchID() {
        return churchID;
    }

    /**
     * @return - The Name of this Church
     */
    public String getName() {
        return name;
    }

    /**
     * @param name  - The Name of this Church
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return - This church's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return - this church's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return - This church's description
     */
    public String getDescription() {
        return description;
    }


    /**
     * @return - a list of members belonging to this church
     */
    public HashMap<String,User> getMembers() {
        return members;
    }

    /**
     * If there are no members to this church, creates a new Members array, else
     * adds a member to the existing array.
     * @param user
     */
    @Exclude
    public void addUser(User user) {
        if(members == null) {
            members = new HashMap<>();
            members.put(user.getId(), user);
        }else{
            members.put(user.getId(), user);
        }
    }
}
