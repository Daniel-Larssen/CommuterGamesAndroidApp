package com.example.android.CommuterGames.FriendRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// TODO Make the FriendRequestController and FriendRequestView to follow the MVC principle.
public class FriendRequest {

    // Member variables representing the title, information and image about the game.
    private int id;
    private String user_1;
    private String user_2;
    private int chat_id;
    private int friendStatus;
    private String chatStatus;

    private static final String TABLE_NAME = "friend_list";
    private static final String KOL_NAME_USERONE = "user_1";
    private static final String KOL_NAME_USERTWO = "user_2";
    private static final String KOL_NAME_CHATID = "chat_id";
    private static final String KOL_NAME_ID = "id";
    private static final String KOL_NAME_FRIENDSTATUS = "friendship_status";
    private static final String KOL_NAME_CHATSTATUS = "chatroom_status";

    /**
     * Creates a FriendRequest-object from a JSONObject.
     *
     * @param jsonGame
     */
    public FriendRequest(JSONObject jsonGame) {

        this.chat_id = jsonGame.optInt(KOL_NAME_CHATID);
        this.user_1 = jsonGame.optString(KOL_NAME_USERONE);
        this.user_2 = jsonGame.optString(KOL_NAME_USERTWO);
        this.friendStatus = jsonGame.optInt(KOL_NAME_FRIENDSTATUS);
        this.id = jsonGame.optInt(KOL_NAME_ID);
        this.chatStatus = jsonGame.optString(KOL_NAME_CHATSTATUS);
    }

    /**
     * Creates a list of FriendRequests from a String.
     *
     * @param jsonVarer
     * @return An ArrayList of FriendRequests
     * @throws JSONException
     * @throws NullPointerException
     */
    public static ArrayList<FriendRequest> createFreindRequestList(String jsonVarer) throws JSONException, NullPointerException {
        ArrayList<FriendRequest> gameList = new ArrayList<FriendRequest>();
        JSONObject jsonData = new JSONObject(jsonVarer);
        JSONArray jsonGameTabell = jsonData.optJSONArray(TABLE_NAME);

        for (int i = 0; i < jsonGameTabell.length(); i++) {
            JSONObject jsonGame = (JSONObject) jsonGameTabell.get(i);
            FriendRequest thisGame = new FriendRequest(jsonGame);
            gameList.add(thisGame);
        }

        return gameList;
    }

    /**
     * Creates a JSONObject of the FriendRequest-object, is used when
     * you need to update or create a database row for friend-list.
     *
     * @return jsonUser is a JSONObject
     */
    public JSONObject getJSONObject() {
        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put(KOL_NAME_CHATSTATUS, this.chatStatus);
            jsonUser.put(KOL_NAME_FRIENDSTATUS, this.friendStatus);
            jsonUser.put(KOL_NAME_CHATID, this.chat_id);
            jsonUser.put(KOL_NAME_USERTWO, this.user_2);
            jsonUser.put(KOL_NAME_USERONE, this.user_1);
            jsonUser.put(KOL_NAME_ID, this.id);

        } catch (JSONException e) {
            return null;
        }

        return jsonUser;
    }

    // --- GET's ---

    public int getId() {
        return id;
    }

    public String getUser_1() {
        return user_1;
    }

    public void setFriendStatus(int friendStatus) {
        this.friendStatus = friendStatus;
    }
}
