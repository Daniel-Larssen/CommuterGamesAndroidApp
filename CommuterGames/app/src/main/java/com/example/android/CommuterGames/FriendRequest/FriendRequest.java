package com.example.android.CommuterGames.FriendRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriendRequest {

    // Member variables representing the title, information and image about the game.
    private int id;
    private String user_1;
    private String user_2;
    private int chat_id;
    private String friendStatus;
    private String chatStatus;

    // Kollone names for the table, and the table name.
    private static final String TABLE_NAME = "friend_list";
    private static final String KOL_NAME_USERONE = "user_1";
    private static final String KOL_NAME_USERTWO = "user_2";
    private static final String KOL_NAME_CHATID = "chat_id";
    private static final String KOL_NAME_ID = "id";
    private static final String KOL_NAME_FRIENDSTATUS = "friendship_status";
    private static final String KOL_NAME_CHATSTATUS = "chatroom_status";


    // Creates a game object from a JSONObject
    public FriendRequest(JSONObject jsonGame) {

        this.chat_id = jsonGame.optInt(KOL_NAME_CHATID);
        this.user_1 = jsonGame.optString(KOL_NAME_USERONE);
        this.user_2 = jsonGame.optString(KOL_NAME_USERTWO);
        this.friendStatus = jsonGame.optString(KOL_NAME_FRIENDSTATUS);
        this.id = jsonGame.optInt(KOL_NAME_ID);
        this.chatStatus = jsonGame.optString(KOL_NAME_CHATSTATUS);
    }

    // Metode som lager en ArrayList med spill-Objekter basert på en string med JSONData
    public static ArrayList<FriendRequest> createFreindRequestList(String jsonVarer) throws JSONException, NullPointerException {
        ArrayList<FriendRequest> gameList = new ArrayList<FriendRequest>();
        JSONObject jsonData = new JSONObject(jsonVarer);
        JSONArray jsonGameTabell = jsonData.optJSONArray(TABLE_NAME);

        for(int i = 0; i < jsonGameTabell.length(); i++) {
            JSONObject jsonGame = (JSONObject) jsonGameTabell.get(i);
            FriendRequest thisGame = new FriendRequest(jsonGame);
            gameList.add(thisGame);
        }
        return gameList;
    }

    // --- GET's ---

    public int getId() {
        return id;
    }

    public String getUser_1() {
        return user_1;
    }
}
