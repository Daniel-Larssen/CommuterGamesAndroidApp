package com.example.android.CommuterGames.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The User object that represents the the users of the
 * logged in user in the database.
 */
public class User {

    // Member variables representing name, username, bio and image of the user.
    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String username;
    private String email;
    private String experiencePoints;

    // The table names and the colon names.
    private static final String TABLE_NAME = "users";
    private static final String KOL_NAME_ID = "id";
    private static final String KOL_NAME_EXPERIENCE = "experience_points";
    private static final String KOL_NAME_FIRSTNAME = "first_name";
    private static final String KOL_NAME_MIDDLENAME = "middle_name";
    private static final String KOL_NAME_LASTNAME = "last_name";
    private static final String KOL_NAME_EMAIL = "email";
    private static final String KOL_NAME_USERNAME = "username";

    /**
     * Empty User constructor
     */
    public User() {}

    /**
     * Constructor for the Game data model, creates a User-object
     * out of a JSONObject
     */
    public User(JSONObject jsonUser) {
        this.firstname = jsonUser.optString(KOL_NAME_FIRSTNAME);
        this.middlename = jsonUser.optString(KOL_NAME_MIDDLENAME);
        this.lastname = jsonUser.optString(KOL_NAME_LASTNAME);
        this.username = jsonUser.optString(KOL_NAME_USERNAME);
        this.email = jsonUser.optString(KOL_NAME_EMAIL);
        this.experiencePoints = jsonUser.optString(KOL_NAME_EXPERIENCE);
        this.id = jsonUser.optInt(KOL_NAME_ID);
    }

    /**
     * Creates a JSONObject of the User-object, is used when
     * you need to update or create a database row for users.
     *
     * @return jsonUser is a JSONObject
     */
    public JSONObject getJSONObject() {
        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put(KOL_NAME_FIRSTNAME, this.firstname);
            jsonUser.put(KOL_NAME_MIDDLENAME, this.middlename);
            jsonUser.put(KOL_NAME_LASTNAME, this.lastname);
            jsonUser.put(KOL_NAME_USERNAME, this.username);
            jsonUser.put(KOL_NAME_EMAIL, this.email);
            jsonUser.put(KOL_NAME_EXPERIENCE, this.experiencePoints);
            jsonUser.put(KOL_NAME_ID, this.id);
        } catch (JSONException e) {
            return null;
        }
        return jsonUser;
    }

    /**
     * Makes a list of user-objects and sends them back in an ArrayList.
     * The objects is created from a string that are sent into as parameters.
     *
     * @param jsonUsers string where tall the users is stored in json.
     * @return ArrayList<User> that holds the User-objects
     * @throws JSONException
     * @throws NullPointerException
     */
    public static ArrayList<User> lagUserListe(String jsonUsers) throws JSONException, NullPointerException {

        // Creates the ArrayList of Users that will be returned.
        ArrayList<User> userList = new ArrayList<User>();

        // Turns the string jsonUsers into a JSONObject.
        JSONObject jsonData = new JSONObject(jsonUsers);

        // Separates the jsonData into a jsonArray, uses the table name.
        JSONArray jsonUserTable = jsonData.optJSONArray(TABLE_NAME);

        // For each of the json-objects in the jsonArray
        for(int i = 0; i < jsonUserTable.length(); i++) {
            JSONObject jsonUser = (JSONObject) jsonUserTable.get(i);
            User thisUser = new User(jsonUser);

            // Adds the user to the ArrayList of users.
            userList.add(thisUser);
        }
        return userList;
    }

    /**
     * Creates a user out of the json object that is sent in.
     * Is used when a single user is collected from the database.
     *
     * @param jsonUser
     * @return the created user,
     * @throws JSONException
     * @throws NullPointerException
     */
    public static User lagUser(String jsonUser) throws JSONException, NullPointerException {

        // Collects the the first user in the
        JSONObject jsonData = new JSONObject(jsonUser);
        JSONArray jsonGameTabell = jsonData.optJSONArray(TABLE_NAME);

        JSONObject firstJsonUser = (JSONObject) jsonGameTabell.get(0);
        User thisUser = new User(firstJsonUser);

        // Returns the user.
        return thisUser;
    }

    // --- GET's ---

    public String getFullName() {return firstname + " " + middlename + " " + lastname; }

    public String getUsername() {return username;}

    public int getId() {return id;}

    public String getFirstname() {return this.firstname;}

    public String getMiddlename() {return this.middlename;}

    public String getLastname() {return this.lastname;}

    // --- SET's ---

    public void setId(int id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}

    public void setMiddlename(String middlename) {this.middlename = middlename;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public void setLastname(String lastname) {this.lastname = lastname;}
}
