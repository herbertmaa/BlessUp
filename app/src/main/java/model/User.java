package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

    /**
     * The ID used to query for this User object from Firebase
     */
    private String userID;

    /**
     * The user's first name
     */
    private String firstName;

    /**
     * The date the user made an account for BlessUp
     */
    private String joinDate;

    /**
     * Default Constructor
     */
    public User() {}

    public User(String userID, String firstName, String lastName, String emailAddress) {
        this.userID = userID;
        this.firstName = firstName;
        this.joinDate = Calendar.getInstance().getTime().toString();
    }

    /**
     * @return - The ID used to query for this User object from Firebase
     */
    public String getId() {
        return userID;
    }

    /**
     * @param userID - The ID used to query for this User object from Firebase
     */
    public void setId(String userID) {
        this.userID = userID;
    }

    /**
     * @return -  The user's first name
     */
    public String getFirstName() {
        return firstName;
    }

}

