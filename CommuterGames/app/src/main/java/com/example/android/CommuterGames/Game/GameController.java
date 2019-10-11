package com.example.android.CommuterGames.Game;

public class GameController {

    private Game model;
    private GameView view;

    public GameController(Game model, GameView view) {
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
