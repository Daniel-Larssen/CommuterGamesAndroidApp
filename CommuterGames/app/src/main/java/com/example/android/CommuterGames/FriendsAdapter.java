package com.example.android.CommuterGames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.CommuterGames.User.User;
import com.example.android.CommuterGames.User.UserController;
import com.example.android.CommuterGames.User.UserView;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the friend data.
 */
class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<User> mFriendData;
    private Context mContext;

    /**
     * Constructor that passes in the friend data and the context.
     *
     * @param friendData ArrayList containing the friend data.
     * @param context    Context of the application.
     */
    FriendsAdapter(Context context, ArrayList<User> friendData) {
        this.mFriendData = friendData;
        this.mContext = context;
    }


    /**
     * Required method for creating the ViewHolder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.friend_list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(FriendsAdapter.ViewHolder holder, int position) {
        // Get current friend.
        User currentUser = mFriendData.get(position);
        UserView userView = new UserView();
        UserController controller = new UserController(currentUser, userView);

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
        return mFriendData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mFullname;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The RootView of the game_list_item_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mFullname = itemView.findViewById(R.id.fullname);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        // Sets the values where they should be
        void bindTo(UserController controller) {
            // Populate the TextViews with data.
            mFullname.setText(controller.getUserUsername());

        }

        /**
         * This happens when one of the games are clicked on.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {
            User currentFriend = mFriendData.get(getAdapterPosition());

            MessageFragment chatFragment = MessageFragment.newInstance();

            // Adds the arguments to the fragment.
            Bundle bundle = new Bundle();

            // This is the id of the user, is used in CHatFragment.
            bundle.putInt("id", currentFriend.getId());
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
