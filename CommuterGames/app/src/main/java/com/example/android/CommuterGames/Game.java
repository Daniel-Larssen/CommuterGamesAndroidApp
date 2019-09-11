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
 * The Game objects is created and managed here.
 * TODO: Collect the objects from the database.
 */
class Game {

    // Member variables representing the title, information and image about the game.
    private String title;
    private String info;
    private final int imageResource;

    /**
     * Constructor for the Game data model.
     *
     * @param title The name if the sport.
     * @param info Information about the sport.
     */
    public Game(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    /**
     * Gets the title of the game.
     *
     * @return The title of the game.
     */
    String getTitle() {
        return title;
    }

    /**
     * Gets the info about the game.
     *
     * @return The info about the game.
     */
    String getInfo() {
        return info;
    }


    /**
     * Gets the image resource belonging to the game.
     *
     * @return
     */
    public int getImageResource() {
        return imageResource;
    }

}