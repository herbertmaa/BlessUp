package model;


import android.location.Location;

import java.util.Calendar;

public class User {

    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private Calendar dateOfBirth;
    private Location address;
    private Church church;
    private Calendar joinDate;
    private Calendar lastActive;
    private char gender;
    private Religion religion;
    private UserType userType;
    private int age;

    public User() {
        this.firstName = "HAM";
        this.lastName = "Demo";
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

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
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

    public Calendar getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Calendar joinDate) {
        this.joinDate = joinDate;
    }

    public Calendar getLastActive() {
        return lastActive;
    }

    public void setLastActive(Calendar lastActive) {
        this.lastActive = lastActive;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

