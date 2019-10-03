package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * The chat-fragment placed in friend. opened whenever a
 * item from the friend-list is clicked on in the friend-list
 * fragment
 */
public class ChatFragment extends Fragment {

    // Used when setting up the RecycleView with messages-objects.
    private RecyclerView mRecyclerView;
    private ArrayList<Message> mFriendsData;
    private MessageAdapter mAdapter;

    public static ArrayList<Message> messageArrayList = new ArrayList<Message>();

    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";

    // Collects all of the games on the database.
    // TODO: Find the users that are a friends with the logged in user
    public static String messageList_url;
    public static RequestQueue queue;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment, gets the arguments, adds in the name.
        final View rootView = inflater.inflate(R.layout.fragment_friend_chat, container, false);

        // Initialize the RecyclerView.
        mRecyclerView = rootView.findViewById(R.id.chatRecyclerView);

        // Set the Layout Manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());

        // Gets the arguments and places it in a bundle.
        Bundle bundle = this.getArguments();

        int user_id = 0;
        // If the bundle is not null.
        if (bundle != null) {
            // Collects the id of the user.
            user_id = bundle.getInt("id");
        }

        // Sets up the query that asks for all messages from user with the collected id.
        messageList_url = ENDPOINT + "/user_chat?filter=user_id,eq," + user_id + "&order=time&transform=1";

        // Starts at the bottom, needed as it is a chat.
        linearLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //----- --- -- https://stackoverflow.com/questions/28599377/how-to-run-volley-in-fragmentnavigation-drawer#28601622 -- --- -----

        // Makes a new Volley with the FriendActivity in the newRequestQueue.
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, messageList_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Tries here to create a list of messages.
                try {
                    messageArrayList.clear();
                    messageArrayList = Message.createMessageList(response);
                    mAdapter = new MessageAdapter(rootView.getContext(), messageArrayList);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ugyldig JSON-data.", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "A error happened with the StringRequest.", Toast.LENGTH_LONG).show();
            }
        });



        // ----------------------------------------------------------------------------------------------------------------------------

        // Adds the stringrequest to the queue.
        queue.add(stringRequest);

        return rootView;
    }

    /**
     * @return Returns a new chatfragment.
     */
    public static ChatFragment newInstance() {
        return new ChatFragment();
    }



}
