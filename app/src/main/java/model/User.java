package model;

import android.location.Location;

import java.util.Calendar;
import java.util.Date;

public class User {

    private String userID = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;
    private Date dateOfBirth = null;
    private String emailAddress = null;
    private Location address = null;
    private Church church = null;
    private Date joinDate = null;
    private Date lastActive = null;
    private String gender = null;
    private String religion = null;
    private String userType = null;
    private int age = 0;

    private User() {}

    public String getEmailAddress() {
        return emailAddress;
    }

    public User(String userID, String firstName, String lastName, String emailAddress){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.joinDate = Calendar.getInstance().getTime();
        this.userType = "MEMBER";
        this.lastActive = joinDate;
        this.religion = "NONE";
    }
    public String getId() {
        return userID;
    }

    public void setId(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public Church getChurch() {
        return church;
    }

    public void setChurch(Church church) {
        this.church = church;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

