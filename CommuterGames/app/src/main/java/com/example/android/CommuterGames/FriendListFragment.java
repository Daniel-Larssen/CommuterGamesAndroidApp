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
 * The fragment that holds the list of friends that a
 * user have.
 */
public class FriendListFragment extends Fragment {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Friend> mFriendsData;
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
        mAdapter = new FriendsAdapter(this.getActivity(), mFriendsData);
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
             * @param viewHolder The friendViewHolder that is being moved
             * @param target The friendViewHolder that you are switching the
             *               original one with.
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
                Collections.swap(mFriendsData, from, to);
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
                mFriendsData.remove(viewHolder.getAdapterPosition());
                // Notify the adapter.
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(mRecyclerView);

        return  rootView;

    }


    /**
     * Initialize the user data from resources.
     * Collects the data for the list-elements.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] firstnames = getResources().getStringArray(R.array.friend_firstnames);
        String[] middlenames = getResources().getStringArray(R.array.friend_middlenames);
        String[] lastnames = getResources().getStringArray(R.array.friend_lastnames);
        String[] bio = getResources().getStringArray(R.array.friend_bio);
        TypedArray friendsImageResources = getResources().obtainTypedArray(R.array.friend_images);

        // Clear the existing data (to avoid duplication).
        mFriendsData.clear();

        // Create the ArrayList of friend objects with the titles and
        // information about each friend
        for (int i = 0; i < firstnames.length; i++) {
            //TODO: CREATE TEMPORARY LIST OF FRIENDS.
            mFriendsData.add(new Friend(firstnames[i], middlenames[i], lastnames[i], lastnames[i], bio[i], friendsImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        friendsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }


    public static FriendListFragment newInstance() {
        return new FriendListFragment();
    }



}
