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
 * The friend object that represents the the friend-users of the
 * logged in user in the database.
 * TODO: Needs to collect the friend-rows from the database.
 */
class Friend {

    // Member variables representing name, username, bio and image of the user.
    private String firstname;
    private String middlename;
    private String lastname;
    private String username;
    private String bio;
    private final int userImage;

    /**
     * Constructor for the Game data model.
     *
     * @param firstname The firstname of the person.
     * @param username username of the user.
     */
    public Friend(String firstname, String middlename, String lastname, String username, String bio, int userImage) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.username = username;
        this.bio = bio;
        this.userImage = userImage;
    }

    /**
     * Gets the title of the sport.
     * @return The full name of the user.
     */
    public String getFullName() {
        return firstname + " " + middlename + " " + lastname;
    }

    public int getUserImage() {
        return userImage;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }
}
