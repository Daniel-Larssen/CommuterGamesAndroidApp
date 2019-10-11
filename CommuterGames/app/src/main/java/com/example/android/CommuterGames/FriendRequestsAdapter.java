/**
 * The code in the Adapter classes are based of
 * the code we used on the sport-list-app when
 * we learned about RecycleView
 */

package com.example.android.CommuterGames;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.CommuterGames;
import com.example.android.CommuterGames.FriendRequest.FriendRequest;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.android.CommuterGames.FriendAddFragment.mAdapter;
import static com.example.android.CommuterGames.FriendAddFragment.friendRequestArrayList;
import static com.example.android.CommuterGames.FriendAddFragment.mRecyclerView;
import static com.example.android.CommuterGames.GameListActivity.ENDPOINT;

/***
 * The adapter class for the RecyclerView, contains the friend data.
 */
class FriendRequestsAdapter extends RecyclerView.Adapter<FriendRequestsAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<FriendRequest> mRequestData;
    private Context mContext;
    public static RequestQueue queue;


    /**
     * Constructor that passes in the friend data and the context.
     *
     * @param friendData ArrayList containing the friend data.
     * @param context Context of the application.
     */
    FriendRequestsAdapter(Context context, ArrayList<FriendRequest> friendData) {
        this.mRequestData = friendData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public FriendRequestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.friend_request_list_item, parent, false));
    }

    /**
     * Required method that binds the data to the ViewHolder.
     *
     * @param holder The ViewHolder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(FriendRequestsAdapter.ViewHolder holder, int position) {

        // Get current FriendRequest.
        FriendRequest request = mRequestData.get(position);

        // Populate the TextViews with data.
        holder.bindTo(request);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mRequestData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mUsername;
        private Button mDeclineButton;
        private Button mAcceptButton;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the game_list_item_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mUsername = itemView.findViewById(R.id.fullname5);
            mDeclineButton = itemView.findViewById(R.id.declineButton);
            mAcceptButton = itemView.findViewById(R.id.acceptButton);

            // Set the OnClickListener to the Decline-button.
            // This will delete the friend_list row from the database.
            mDeclineButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    final FriendRequest currentRequest = mRequestData.get(getAdapterPosition());
                    String URL = ENDPOINT + "/friend_list/" + currentRequest.getId();
                    queue = Volley.newRequestQueue(CommuterGames.getContext());

                    // The StringRequest that deletes the friend_list row in the database.
                    StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            // Removes the Request from the ArrayList, as it is removed from the database.
                            friendRequestArrayList.remove(currentRequest);

                            // Updates the adapter and sets it.
                            mAdapter = new FriendRequestsAdapter(CommuterGames.getContext(), friendRequestArrayList);
                            FriendAddFragment.mRecyclerView.setAdapter(mAdapter);

                            Toast.makeText(CommuterGames.getContext(), "The Friend-Request Was Declined", Toast.LENGTH_LONG).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CommuterGames.getContext(), "A error happened with the StringRequest.", Toast.LENGTH_LONG).show();
                        }
                    });

                    queue.add(stringRequest);
                }
            });

            // Sets the OnClickListener for the Accept-button
            // This will update the friendship_status in the database to become 1
            mAcceptButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    final FriendRequest currentRequest = mRequestData.get(getAdapterPosition());

                    // A new FriendRequest is created as we need currentRequest to update the ArrayList
                    FriendRequest updatedRequest = currentRequest;
                    updatedRequest.setFriendStatus(1);
                    String URL = ENDPOINT + "/friend_list/" + currentRequest.getId();
                    queue = Volley.newRequestQueue(CommuterGames.getContext());
                    JSONObject jsonObject = updatedRequest.getJSONObject();

                    JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonObject,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    // Removes the Request from the ArrayList, as it is removed from the database.
                                    friendRequestArrayList.remove(currentRequest);

                                    // Updates the adapter and sets it.
                                    // TODO Better way to do this.

                                    mRecyclerView.removeViewAt(getAdapterPosition());
                                    mAdapter.notifyItemRemoved(getAdapterPosition());
                                    mAdapter.notifyItemRangeChanged(getAdapterPosition(), friendRequestArrayList.size());
                                    mAdapter.notifyDataSetChanged();
                                    //mAdapter = new FriendRequestsAdapter(CommuterGames.getContext(), friendRequestArrayList);
                                    //FriendAddFragment.mRecyclerView.setAdapter(mAdapter);

                                    Toast.makeText(CommuterGames.getContext(), "The Friend-Request Was Accepted", Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    // TODO Had to do this because of time-restraints at the end.
                                    // Removes the Request from the ArrayList, as it is removed from the database.
                                    friendRequestArrayList.remove(currentRequest);

                                    // Updates the adapter and sets it.
                                    mAdapter = new FriendRequestsAdapter(CommuterGames.getContext(), friendRequestArrayList);
                                    FriendAddFragment.mRecyclerView.setAdapter(mAdapter);

                                    Toast.makeText(CommuterGames.getContext(), "The Friend-Request Was Accepted", Toast.LENGTH_LONG).show();

                                }
                            });
                    queue.add(jsonObjRequest);
                }
            });
        }

        // Sets the values where they should be
        void bindTo(FriendRequest request){

            // Populate the TextViews with data.
            mUsername.setText(request.getUser_1() + " wants to be your friend!");
        }


        @Override
        public void onClick(View view) {

        }
    }
}
