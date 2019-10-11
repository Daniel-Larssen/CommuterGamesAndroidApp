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
public class AddFriendFragment extends Fragment {


    public AddFriendFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment, gets the arguments, adds in the name.
        final View rootView = inflater.inflate(R.layout.fragment_friend_add, container, false);

        // Set the Layout Manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());

        return  rootView;

    }

    /**
     * @return Returns a new chatfragment.
     */
    public static AddFriendFragment newInstance() {
        return new AddFriendFragment();
    }


}
