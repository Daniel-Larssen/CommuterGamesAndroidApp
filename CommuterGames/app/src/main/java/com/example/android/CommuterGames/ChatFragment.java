package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * The chat-fragment placed in friend. opened whenever a
 * item from the friend-list is clicked on in the friend-list
 * fragment
 */
public class ChatFragment extends Fragment {

    // Used when setting up the RecycleView with messages-objects.
    private RecyclerView mRecyclerView;
    private ArrayList<Message> mFriendsData;
    private MessageAdapter mAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment, gets the arguments, adds in the name.
        final View rootView = inflater.inflate(R.layout.fragment_friend_chat, container, false);

        // Initialize the RecyclerView.
        mRecyclerView = rootView.findViewById(R.id.chatRecyclerView);

        // Set the Layout Manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());

        // Starts at the bottom, needed as it is a chat.
        linearLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // Initialize the ArrayList that will contain the data.
        mFriendsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new MessageAdapter(this.getActivity(), mFriendsData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();
        return  rootView;

    }

    /**
     * @return Returns a new chatfragment.
     */
    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    /**
     * Initialize messages from the string-array they currently lies in.
     */
    private void initializeData() {

        // Get the resources from the array in the file.
        // TODO: Collect the messages from the database instead.
        String[] usernames = getResources().getStringArray(R.array.example_messages_id);
        String[] messages = getResources().getStringArray(R.array.example_messages);

        // Clear the existing data (to avoid duplication).
        mFriendsData.clear();

        // Create the ArrayList of Message objects with the username and messages
        for (int i = 0; i < usernames.length; i++) {
            mFriendsData.add(new Message(usernames[i], messages[i]));
        }

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }



}
