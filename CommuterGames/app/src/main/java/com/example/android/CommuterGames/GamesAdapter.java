/**
 * The code in the Adapter classes are based of
 * the code we used on the sport-list-app when
 * we learned about RecycleView
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

import com.example.android.CommuterGames.Game.Game;
import com.example.android.CommuterGames.Game.GameController;
import com.example.android.CommuterGames.Game.GameView;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the Game data.
 */
class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Game> mGamesData;
    private Context mContext;
    boolean isFragmentDisplayed = false;

    /**
     * Constructor that passes in the games data and the context.
     *
     * @param gameArrayList ArrayList containing the game data.
     * @param context       Context of the application.
     */
    GamesAdapter(Context context, ArrayList<Game> gameArrayList) {
        this.mGamesData = gameArrayList;
        this.mContext = context;
    }

    /**
     * Required method for creating the ViewHolder objects.
     *
     * @param parent   The ViewGroup into which the new View will be
     *                 added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.game_list_item, parent, false));
    }

    /**
     * Required method that binds the data to the ViewHolder.
     *
     * @param holder   The ViewHolder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(GamesAdapter.ViewHolder holder, int position) {

        // Collects the Game object from the position of the adapter and adds in into a MessageController.
        Game currentGame = mGamesData.get(position);
        GameView view = new GameView();
        GameController controller = new GameController(currentGame, view);

        // Populate the TextViews with data.
        holder.bindTo(controller);
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
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mGenreText;
        private ImageView mGamesImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * Creates the views creates a onClick listener.
         *
         * @param itemView The rootview of the game_list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.gameBio);
            mGamesImage = itemView.findViewById(R.id.gamesImage);
            mGenreText = itemView.findViewById(R.id.genre);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        // Takes in the MessageController and attaches the data to the text.
        void bindTo(GameController controller) {

            // Cuts the game description down to 100 letters if it is to long, to create uniformity.
            String cutDesc = controller.getGameDescription();
            if (cutDesc.length() > 100) {
                cutDesc = cutDesc.substring(0, 100) + "...";
            }

            // Populate the TextViews with data.
            mTitleText.setText(controller.getGameTitle());
            mInfoText.setText(cutDesc);
            mGenreText.setText(controller.getGameGenre());

        }

        /**
         * This happens when one of the games are clicked on.
         * Opens up the game details
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {

            // Creates a detailFragment at click, will show details of the game
            GameDetailFragment detailFragment = GameDetailFragment.newInstance();

            // Adds the arguments to the fragment.
            Game currentGame = mGamesData.get(getAdapterPosition());
            GameView gameView = new GameView();
            GameController controller = new GameController(currentGame, gameView);

            // Bundles the arguments and sets them.
            Bundle bundle = new Bundle();
            bundle.putString("title", controller.getGameTitle());
            bundle.putString("creator", controller.getGameCreator());
            bundle.putString("genre", controller.getGameGenre());
            bundle.putString("description", controller.getGameDescription());
            bundle.putInt("rating", controller.getGameRating());
            bundle.putInt("image_resource", controller.getGameImageResource());
            detailFragment.setArguments(bundle);

            // Get the FragmentManager and start a transaction.
            // A transaction wraps all the the Fragment operations together before the transaction is committed.
            FragmentTransaction fragmentTransaction = ((FragmentActivity) mContext).getSupportFragmentManager()
                    .beginTransaction().replace(R.id.detail_fragment_container, detailFragment).addToBackStack(null);

            // Add the ChatFragment, added to backstack of fragment transactions, which allows the user to return
            // to the previous Fragment state pressed by the back button, calls commit for the transaction to take effect.
            fragmentTransaction.commit();

        }
    }
}
