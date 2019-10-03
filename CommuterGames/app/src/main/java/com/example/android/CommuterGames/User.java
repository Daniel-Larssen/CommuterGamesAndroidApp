/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.CommuterGames;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The friend object that represents the the friend-users of the
 * logged in user in the database.
 * TODO: Needs to collect the friend-rows from the database.
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

    private static final String TABLE_NAME = "users";
    private static final String KOL_NAME_ID = "id";
    private static final String KOL_NAME_EXPERIENCE = "experience_points";
    private static final String KOL_NAME_FIRSTNAME = "first_name";
    private static final String KOL_NAME_MIDDLENAME = "middle_name";
    private static final String KOL_NAME_LASTNAME = "last_name";
    private static final String KOL_NAME_EMAIL = "email";
    private static final String KOL_NAME_USERNAME = "username";


    /**
     * Constructor for the Game data model.
     *
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

    public User() {

    }

    public JSONObject getJSONObject() {
        JSONObject jsonVare = new JSONObject();
        try {
            jsonVare.put(KOL_NAME_FIRSTNAME, this.firstname);
            jsonVare.put(KOL_NAME_MIDDLENAME, this.middlename);
            jsonVare.put(KOL_NAME_LASTNAME, this.lastname);
            jsonVare.put(KOL_NAME_USERNAME, this.username);
            jsonVare.put(KOL_NAME_EMAIL, this.email);
            jsonVare.put(KOL_NAME_EXPERIENCE, this.experiencePoints);
            jsonVare.put(KOL_NAME_ID, this.id);
        } catch (JSONException e) {
            return null;
        }
        return jsonVare;
    }

    // Metode som lager en ArrayList med bruker-objekter basert på en streng med JSONdata
    public static ArrayList<User> lagUserListe(String jsonUsers) throws JSONException, NullPointerException {
        ArrayList<User> userList = new ArrayList<User>();
        JSONObject jsonData = new JSONObject(jsonUsers);
        JSONArray jsonGameTabell = jsonData.optJSONArray(TABLE_NAME);

        for(int i = 0; i < jsonGameTabell.length(); i++) {
            JSONObject jsonUser = (JSONObject) jsonGameTabell.get(i);
            User thisUser = new User(jsonUser);
            userList.add(thisUser);
        }

        return userList;
    }

    // Used in the login part of the application, collects a single user.
    public static User lagUser(String jsonUser) throws JSONException, NullPointerException {

        // Collects the the first user in the
        JSONObject jsonData = new JSONObject(jsonUser);
        JSONArray jsonGameTabell = jsonData.optJSONArray(TABLE_NAME);

        JSONObject firstJsonUser = (JSONObject) jsonGameTabell.get(0);
        User thisUser = new User(firstJsonUser);

        // Returns the user.
        return thisUser;
    }

    /**
     * Gets the title of the sport.
     * @return The full name of the user.
     */
    public String getFullName() {
        return firstname + " " + middlename + " " + lastname;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {return id;}

    public String getFirstname() {return this.firstname;}
    public String getMiddlename() {return this.middlename;}
    public String getLastname() {return this.lastname;}

    public void setId(int id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}

    public void setMiddlename(String middlename) {this.middlename = middlename;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public void setLastname(String lastname) {this.lastname = lastname;}
}
