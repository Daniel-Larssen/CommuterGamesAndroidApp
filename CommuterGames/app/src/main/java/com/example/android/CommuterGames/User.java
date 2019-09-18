package com.example.android.CommuterGames;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    String username;

    // Constructor based on a JSONObject.
    public User(JSONObject jsonUser) {
        this.username = jsonUser.optString("username");
    }

    // Normal Constructor.
    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

    public static ArrayList<User> createUserList (String jsonUsers) throws JSONException, NullPointerException {

        ArrayList<User> userList = new ArrayList<User>();

        JSONObject jsonAllUsers = new JSONObject(jsonUsers);

        // jsonData?
        JSONArray jsonUserArray = jsonAllUsers.optJSONArray("users");

        for(int i = 0; i < jsonUserArray.length(); i++) {

            JSONObject jsonUser = (JSONObject) jsonUserArray.get(i);

            User thisUser = new User(jsonUser);

            userList.add(thisUser);
        }

        return userList;

    }


}
