package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.CommuterGames.Game.Game;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends Fragment {

    // Variables representing the RecycleView
    private RecyclerView mRecyclerView;
    private ArrayList<Game> mGamesData;
    private GamesAdapter mAdapter;

    public GameListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_game_list, container, false);

        // Initialize the RecyclerView.
        mRecyclerView = rootView.findViewById(R.id.gameRecyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // Initialize the ArrayList that will contain the data.
        mGamesData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new GamesAdapter(this.getActivity(), GameListActivity.gameArrayList);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;

    }

    public static GameListFragment newInstance() {
        return new GameListFragment();
    }

}
