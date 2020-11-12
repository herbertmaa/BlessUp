package model;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;

public class Church implements Serializable {

    private String churchID;
    private String name;
    private String address;
    private String phoneNumber;
    private String religion;
    private String description;
    private ArrayList<User> members;

    public Church(String churchID, String name, String address, String phoneNumber, String religion, String description, ArrayList<User> members) {
        this.churchID = churchID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.religion = religion;
        this.description = description;
        this.members = members;
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

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }
}
