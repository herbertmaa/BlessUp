package model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;

public class Church implements Serializable {

    private String churchID;
    private String name;
    private String address;
    private String phoneNumber;
    private String religion;
    private String description;
    private String imageURL;
    public HashMap<String, User> members;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Church() {}
    public Church(String churchID, String name, String address, String phoneNumber, String religion, String description) {
        this.churchID = churchID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.religion = religion;
        this.description = description;
        this.members = new HashMap<>();
        this.imageURL = null;
    }

    public String getChurchID() {
        return churchID;
    }

    public void setChurchID(String churchID) {
        this.churchID = churchID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String,User> getMembers() {
        return members;
    }

    public void setMembers(HashMap<String, User> members) {
        this.members = members;
    }

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
