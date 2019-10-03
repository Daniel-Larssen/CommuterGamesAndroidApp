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
 * Data model for each row of the messages that lie in the database.
 */
class Message {

    // Member variables representing name, username, bio and image of the user.
    private String id;
    private String room_id;
    private String user_id;
    private String username;
    private String time;
    private String text;
    private String viewed;

    private static final String TABLE_NAME = "user_chat";
    private static final String KOL_NAME_ID = "id";
    private static final String KOL_NAME_ROOM_ID = "room_id";
    private static final String KOL_NAME_USER_ID = "user_id";
    private static final String KOL_NAME_USERNAME = "username";
    private static final String KOL_NAME_TIME = "time";
    private static final String KOL_NAME_TEXT = "text";
    private static final String KOL_NAME_VIEWED = "viewed";

    public Message(JSONObject jsonMessage) {
        this.id = jsonMessage.optString(KOL_NAME_ID);
        this.room_id = jsonMessage.optString(KOL_NAME_ROOM_ID);
        this.user_id = jsonMessage.optString(KOL_NAME_USER_ID);
        this.username = jsonMessage.optString(KOL_NAME_USERNAME);
        this.time = jsonMessage.optString(KOL_NAME_TIME);
        this.text = jsonMessage.optString(KOL_NAME_TEXT);
        this.viewed = jsonMessage.optString(KOL_NAME_VIEWED);
    }

    public static ArrayList<Message> createMessageList(String jsonMessages) throws JSONException, NullPointerException {
        ArrayList<Message> messageList = new ArrayList<Message>();
        JSONObject jsonData = new JSONObject(jsonMessages);
        JSONArray jsonMessageTable = jsonData.optJSONArray(TABLE_NAME);

        for(int i = 0; i < jsonMessageTable.length(); i++) {
            JSONObject jsonMessage = (JSONObject) jsonMessageTable.get(i);
            Message thisUser = new Message(jsonMessage);
            messageList.add(thisUser);
        }

        return messageList;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }
}
