package com.example.android.CommuterGames.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Game {

    // Member variables representing the title, information and image about the game.
    private int id;
    private String title;
    private String description;
    private String genre;
    private String creator;
    private int rating;
    private final int imageResource;

    // Kollone names for the table, and the table name.
    private static final String TABLE_NAME = "games";
    private static final String KOL_NAME_TITLE = "title";
    private static final String KOL_NAME_GENRE = "genre";
    private static final String KOL_NAME_CREATOR = "creator";
    private static final String KOL_NAME_ID = "id";
    private static final String KOL_NAME_RATING = "rating";
    private static final String KOL_NAME_DESC = "description";


    // Creates a game object from a JSONObject
    public Game(JSONObject jsonGame) {

        this.title = jsonGame.optString(KOL_NAME_TITLE);
        this.creator = jsonGame.optString(KOL_NAME_CREATOR);
        this.description = jsonGame.optString(KOL_NAME_DESC);
        this.genre = jsonGame.optString(KOL_NAME_GENRE);
        this.id = jsonGame.optInt(KOL_NAME_ID);
        this.rating = jsonGame.optInt(KOL_NAME_RATING);
        imageResource = 1;
    }

    // Metode som lager en ArrayList med spill-Objekter basert på en string med JSONData
    public static ArrayList<Game> createGameList(String jsonVarer) throws JSONException, NullPointerException {
        ArrayList<Game> gameList = new ArrayList<Game>();
        JSONObject jsonData = new JSONObject(jsonVarer);
        JSONArray jsonGameTabell = jsonData.optJSONArray(TABLE_NAME);

        for(int i = 0; i < jsonGameTabell.length(); i++) {
            JSONObject jsonGame = (JSONObject) jsonGameTabell.get(i);
            Game thisGame = new Game(jsonGame);
            gameList.add(thisGame);
        }
        return gameList;
    }

    // --- GET's ---

    public int getImageResource() {
        return imageResource;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }

    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
}
