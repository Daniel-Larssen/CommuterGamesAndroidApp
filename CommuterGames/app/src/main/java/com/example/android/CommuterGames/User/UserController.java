package com.example.android.CommuterGames.User;

import com.example.android.CommuterGames.Game.Game;
import com.example.android.CommuterGames.Game.GameView;

public class UserController {

    private User model;
    private UserView view;

    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    // --- GET's ---

    public String getUserFullName() { return model.getFullName(); }

    public String getUserUsername() { return model.getUsername(); }

    public int getUserId() {return model.getId();}

    public String getUserFirstname() {return model.getFirstname();}

    public String getUserMiddlename() {return model.getMiddlename();}

    public String getUserLastname() {return model.getLastname();}

    // --- SET's ---

    public void setUserId(int id) {model.setId(id);}

    public void setUserUsername(String username) {model.setUsername(username);}

    public void setUserMiddlename(String middlename) {model.setMiddlename(middlename);}

    public void setUserFirstname(String firstname) {model.setFirstname(firstname);}

    public void setUserLastname(String lastname) {model.setLastname(lastname);}
}
