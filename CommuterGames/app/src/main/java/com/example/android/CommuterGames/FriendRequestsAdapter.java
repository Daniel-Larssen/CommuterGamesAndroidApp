/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.CommuterGames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.CommuterGames.FriendRequest.FriendRequest;
import com.example.android.CommuterGames.User.User;
import com.example.android.CommuterGames.User.UserController;
import com.example.android.CommuterGames.User.UserView;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the friend data.
 */
class FriendRequestsAdapter extends RecyclerView.Adapter<FriendRequestsAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<FriendRequest> mFriendData;
    private Context mContext;


    /**
     * Constructor that passes in the friend data and the context.
     *
     * @param friendData ArrayList containing the friend data.
     * @param context Context of the application.
     */
    FriendRequestsAdapter(Context context, ArrayList<FriendRequest> friendData) {
        this.mFriendData = friendData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public FriendRequestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.friend_request_list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(FriendRequestsAdapter.ViewHolder holder, int position) {
        // Get current friend.
        FriendRequest request = mFriendData.get(position);

        // Populate the textviews with data.
        holder.bindTo(request);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mFriendData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mFullname;
        private TextView mUsername;
        private ImageView mUserImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the game_list_item_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mFullname = itemView.findViewById(R.id.fullname5);
            //mUserImage = itemView.findViewById(R.id.userimage);
            //mUsername = itemView.findViewById(R.id.username);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        // Sets the values where they should be
        void bindTo(FriendRequest request){
            // Populate the textviews with data.
            mFullname.setText(request.getUser_1());

            // Load the images into the ImageView using the Glide library.
            //Glide.with(mContext).load(R.into(mUserImage);
        }

        /**
         * This happens when one of the games are clicked on.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {
            FriendRequest currentRequest = mFriendData.get(getAdapterPosition());

                MessageFragment chatFragment = MessageFragment.newInstance();

                // Adds the arguments to the fragment.
                Bundle bundle = new Bundle();

                // This is the id of the user, is used in CHatFragment.
                bundle.putInt("id", currentRequest.getId());
                chatFragment.setArguments(bundle);

                // Get the FragmentManager and start a transaction.
                // A transaction wraps all the the Fragment operations together before the transaction is committed.
                    FragmentTransaction fragmentTransaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.chat_fragment_container, chatFragment).addToBackStack(null);

                    // Add the ChatFragment, added to backstack of fragment transactions, which allows the user to return
                    // to the previous Fragment state pressed by the back button., calls commit for the transaction to take effect.
                    fragmentTransaction.commit();




        }
    }
}
