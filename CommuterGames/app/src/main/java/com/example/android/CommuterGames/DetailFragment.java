package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

/**
 * The DetailFragment shows the details of the game
 * that is clicked in the list of games in GamesListFragment,
 * opens up in gamesListActivity.
 */
public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment, gets teh arguments, adds in the name.
        final View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        // Gets the arguments and places it in a bundle.
        Bundle bundle = this.getArguments();

        // If the bundle is not null.
        if (bundle != null) {

            // Collects the title of the game.
            String title = bundle.getString("title");
            String desc = bundle.getString("description");
            String creator = bundle.getString("creator");
            String genre = bundle.getString("genre");
            int rating = bundle.getInt("rating");


            // Initialize the views.
            TextView titleView = rootView.findViewById(R.id.titleTextView);
            ImageView gameImage = rootView.findViewById(R.id.gameImageDetail);
            TextView genreView = rootView.findViewById(R.id.genreTextView);
            TextView descView = rootView.findViewById(R.id.descTextView);

            // Set the text from the Intent extra.
            titleView.setText(title);
            genreView.setText(genre);
            descView.setText(desc);

            // Load the image using the Glide library and the Intent extra.
            //Glide.with(this).load(bundle.getInt("image_resource",0)).into(gameImage);
        }

        return  rootView;

    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

}
