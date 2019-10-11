package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.android.CommuterGames.User.User;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * The activity that holds the fragments holding the friend-list
 * and the chat.
 */
public class FriendActivity extends Activity implements Response.Listener<String>, Response.ErrorListener {

    // The list of users that the logged in user is friends with.
    public static ArrayList<User> userArrayList = new ArrayList<User>();

    // Collects all of the games on the database.
    public final static String userlist_URL = ENDPOINT + "/users?transform=1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        // Adds the bottom navigation.
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_notifications);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Collects all the friends into the list, also created the new fragment in onResponse.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, userlist_URL, this, this);
        readDbObject(stringRequest);


    }

    /**
     * @param response
     */
    public void onResponse(String response) {

        // If the friend fragment is already created, used when the orientation is flipped.
        if (findViewById(R.id.friend_fragment_container) != null) {

            // Tries to make a list of User-objects with the response string.
            try {
                userArrayList = User.lagUserListe(response);
            } catch (JSONException e) {
                Toast.makeText(this, "Ugyldig JSON-data.", Toast.LENGTH_LONG).show();
            }

            FriendListFragment friendListFragment = new FriendListFragment();

            // Only really used if you want the extras that are sent with the intent.
            friendListFragment.setArguments(getIntent().getExtras());

            // Places the fragment in the FrameLayout named friend_fragment_container.
            getSupportFragmentManager().beginTransaction().add(R.id.friend_fragment_container, friendListFragment).commit();
        }
    }



    /**
     * This happens when the button on the top of the screen is clicked on,
     * it shows the fragment that holds the friend-requests and the search-
     * for-friend part.
     *
     * @param view View that is clicked.
     */
    public void openAddFriendFragment(View view) {

        // Sets up the fragment that holds the search-for-friend part and friend requests.
        FriendAddFragment addFriendFragment = FriendAddFragment.newInstance();

        // Get the FragmentManager and start a transaction.
        // A transaction wraps all the the Fragment operations together before the transaction is committed.
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.friend_fragment_container, addFriendFragment).addToBackStack(null);

        // Add the ChatFragment, added to backstack of fragment transactions, which allows the user to return
        // to the previous Fragment state pressed by the back button., calls commit for the transaction to take effect.
        fragmentTransaction.commit();

    }

}
