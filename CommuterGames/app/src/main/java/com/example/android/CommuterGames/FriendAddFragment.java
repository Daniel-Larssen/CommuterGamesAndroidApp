package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.CommuterGames.FriendRequest.FriendRequest;
import com.example.android.CommuterGames.data.LoginRepository;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * The chat-fragment placed in friend. opened whenever a
 * item from the friend-list is clicked on in the friend-list
 * fragment
 */
public class FriendAddFragment extends Fragment {


    // Used when setting up the RecycleView with messages-objects.
    public static RecyclerView mRecyclerView;
    public static FriendRequestsAdapter mAdapter;
    public static ArrayList<FriendRequest> friendRequestArrayList = new ArrayList<>();
    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";
    public static String URL;
    public static RequestQueue queue;

    public FriendAddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment, gets the arguments, adds in the name.
        final View rootView = inflater.inflate(R.layout.fragment_friend_add, container, false);

        // Initialize the RecyclerView.
        mRecyclerView = rootView.findViewById(R.id.requestRecyclerView);

        // Set the Layout Manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());

        // Sets up the query that asks for all messages from user with the collected id.
        URL = ENDPOINT + "/friend_list?filter=user_2,eq," + LoginRepository.user.getUserId() + "&filter=friendship_status,eq,0&transform=1";

        // Starts at the bottom, needed as it is a chat.
        linearLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // Found this way of setting up the StringRequest here.
        // https://stackoverflow.com/questions/28599377/how-to-run-volley-in-fragmentnavigation-drawer#28601622

        // Makes a new Volley with the FriendActivity in the newRequestQueue.
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Tries here to create a list of FriendRequests.
                try {
                    friendRequestArrayList.clear();
                    friendRequestArrayList = FriendRequest.createFreindRequestList(response);
                    mAdapter = new FriendRequestsAdapter(rootView.getContext(), friendRequestArrayList);
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

        // Adds the StringRequest to the queue.
        queue.add(stringRequest);
        return rootView;
    }

    /**
     * @return Returns a new chatfragment.
     */
    public static FriendAddFragment newInstance() {
        return new FriendAddFragment();
    }


}
