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
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<Game> mGamesData;
    private Context mContext;
    boolean isFragmentDisplayed = false;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param gameArrayList ArrayList containing the game data.
     * @param context Context of the application.
     */
    GamesAdapter(Context context, ArrayList<Game> gameArrayList) {
        this.mGamesData = gameArrayList;
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
    public GamesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.game_list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(GamesAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Game currentGame = mGamesData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentGame);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mGamesData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mGenreText;
        private ImageView mGamesImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the game_list_item_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.gameBio);
            mGamesImage = itemView.findViewById(R.id.sportsImage);
            mGenreText = itemView.findViewById(R.id.genre);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Game currentGame){
            // Populate the textviews with data.
            mTitleText.setText(currentGame.getTitle());

            String cutDesc = currentGame.getDescription();
            if(cutDesc.length() > 100) {
                cutDesc = cutDesc.substring(0,100) + "...";
            }

            mInfoText.setText(cutDesc);
            mGenreText.setText(currentGame.getGenre());


            // Load the images into the ImageView using the Glide library.
            //Glide.with(mContext).load(currentGame.getImageResource()).into(mGamesImage);
        }

        /**
         * This happens when one of the games are clicked on.
         * Opens up the game details
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {

                DetailFragment detailFragment = DetailFragment.newInstance();

                // Adds the arguments to the fragment.
                Game currentGame = mGamesData.get(getAdapterPosition());
                Bundle bundle = new Bundle();
                bundle.putString("title", currentGame.getTitle());
                bundle.putString("creator", currentGame.getCreator());
                bundle.putString("genre", currentGame.getGenre());
                bundle.putString("description", currentGame.getDescription());
                bundle.putInt("rating", currentGame.getRating());
                bundle.putInt("image_resource", currentGame.getImageResource());
                detailFragment.setArguments(bundle);

                // Checks what orientation it has, and chooses which to update.
                if(mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    // Get the FragmentManager and start a transaction.
                    // A transaction wraps all the the Fragment operations together before the transaction is committed.
                    FragmentTransaction fragmentTransaction = ((FragmentActivity) mContext).getSupportFragmentManager()
                            .beginTransaction().replace(R.id.detail_fragment_container, detailFragment).addToBackStack(null);

                    // Add the ChatFragment, added to backstack of fragment transactions, which allows the user to return
                    // to the previous Fragment state pressed by the back button, calls commit for the transaction to take effect.
                    fragmentTransaction.commit();

                } else {

                    FragmentTransaction fragmentTransaction = ((FragmentActivity) mContext).getSupportFragmentManager()
                            .beginTransaction().replace(R.id.detail_fragment_container, detailFragment).addToBackStack(null);

                    fragmentTransaction.commit();
                }
        }
    }

}
