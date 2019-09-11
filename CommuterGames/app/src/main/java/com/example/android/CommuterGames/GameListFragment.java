package com.example.android.CommuterGames;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;


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
        mAdapter = new GamesAdapter(this.getActivity(), mGamesData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();

        // Helper class for creating swipe to dismiss and drag and drop functionality.
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            /**
             * Defines the drag and drop functionality.
             *
             * @param recyclerView The RecyclerView that contains the list items
             * @param viewHolder The gamesViewHolder that is being moved
             * @param target The gamesViewHolder that you are switching the original one with.
             * @return true if the item was moved, false otherwise
             */
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // Get the from and to positions.
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                // Swap the items and notify the adapter.
                Collections.swap(mGamesData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Defines the swipe to dismiss functionality.
             *
             * @param viewHolder The viewholder being swiped.
             * @param direction The direction it is swiped in.
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Remove the item from the dataset.
                mGamesData.remove(viewHolder.getAdapterPosition());
                // Notify the adapter.
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(mRecyclerView);

        return  rootView;

    }


    /**
     * Initialize the games data from resources.
     * Collects the data for the list-elements.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] gamesList = getResources().getStringArray(R.array.game_titles);
        String[] gamesInfo = getResources().getStringArray(R.array.game_info);
        TypedArray gamesImageResources = getResources().obtainTypedArray(R.array.game_images);

        // Clear the existing data (to avoid duplication).
        mGamesData.clear();

        // Create the ArrayList of games objects with the titles and
        // information about each game
        for (int i = 0; i < gamesList.length; i++) {
            //TODO: CREATE TEMPORARY LIST OF FRIENDS.
            mGamesData.add(new Game(gamesList[i], gamesInfo[i], gamesImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        gamesImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }


    public static GameListFragment newInstance() {
        return new GameListFragment();
    }



}
