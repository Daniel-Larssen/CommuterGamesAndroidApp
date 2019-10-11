package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.CommuterGames.User.User;

import java.util.ArrayList;


/**
 * The fragment that holds the list of friends that a
 * user have.
 */
public class FriendListFragment extends Fragment {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<User> mFriendsData;
    private FriendsAdapter mAdapter;

    public FriendListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);

        // Initialize the RecyclerView.
        mRecyclerView = rootView.findViewById(R.id.friendRecyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // Initialize the ArrayList that will contain the data.
        mFriendsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new FriendsAdapter(this.getActivity(), FriendActivity.userArrayList);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;

    }
}
