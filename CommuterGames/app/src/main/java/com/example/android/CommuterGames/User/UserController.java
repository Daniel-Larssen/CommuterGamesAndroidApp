package com.example.android.CommuterGames.User;

import com.example.android.CommuterGames.Game.Game;
import com.example.android.CommuterGames.Game.GameView;

public class UserController {

    private Game model;
    private GameView view;

    public UserController(Game model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public int getGameImageResource() {
        return model.getImageResource();
    }

    public String getGameGenre() {
        return model.getGenre();
    }

    public String getGameTitle() {
        return model.getTitle();
    }

    public String getGameDescription() {
        return model.getDescription();
    }

    public String getGameCreator() {
        return model.getCreator();
    }

    public int getGameRating() {
        return model.getRating();
    }

    public int getGameId() {
        return model.getId();
    }

}
