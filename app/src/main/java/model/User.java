package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String emailAddress;
    private String joinDate;
    private String lastActive;
    private String gender;
    private String religion;
    private String userType;
    private int age = 0;

    public User() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User(String userID, String firstName, String lastName, String emailAddress) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.joinDate = Calendar.getInstance().getTime().toString();
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
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

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}

