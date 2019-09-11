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

/**
 * Data model for each row of the messages that lie in the database.
 */
class Message {

    // Member variables representing name, username, bio and image of the user.
    private String message;
    private String username;

    /**
     * Constructor for the Game data model.
     *

     */
    public Message(String message, String username) {

        this.message = message;
        this.username = username;
    }


    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
}
