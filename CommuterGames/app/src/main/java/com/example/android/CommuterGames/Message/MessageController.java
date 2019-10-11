package com.example.android.CommuterGames.Message;

public class MessageController {

    private Message model;
    private MessageView view;

    public MessageController(Message model, MessageView view) {
        this.model = model;
        this.view = view;
    }

    // --- GET's ---
    public String getMessageText() {return model.getText();}

    public String getMessageUsername() { return model.getUsername(); }

}
