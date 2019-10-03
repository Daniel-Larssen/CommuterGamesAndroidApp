package com.example.android.CommuterGames;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * The activity that holds the fragments holding the friend-list
 * and the chat.
 */
public class FriendActivity extends Activity implements Response.Listener<String>, Response.ErrorListener {

    // The list of users that the logged in user is friends with.
    public static ArrayList<User> userArrayList = new ArrayList<User>();
    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";

    // Collects all of the games on the database.
    // TODO: Find the users that are a friends with the logged in user
    public final static String userlist_URL = ENDPOINT + "/users?transform=1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        // Adds the bottom navigation.
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_notifications);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Collects all the games into the list, also created the new fragment in onResponse.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, userlist_URL, this, this);
        readData(stringRequest);

    }

    public void onResponse(String response) {

        // If the friend fragment is already created, used when the orientation is flipped.
        if (findViewById(R.id.friend_fragment_container) != null) {

            try {
                userArrayList = User.lagUserListe(response);
            } catch (JSONException e) {
                Toast.makeText(this, "Ugyldig JSON-data.", Toast.LENGTH_LONG).show();
            }

            FriendListFragment friendListFragment = new FriendListFragment();

            // Only really used if you want the exstras that are sent with the intent.
            friendListFragment.setArguments(getIntent().getExtras());

            // Places the fragment in the FrameLayout named friend_fragment_container.
            getSupportFragmentManager().beginTransaction().add(R.id.friend_fragment_container, friendListFragment).commit();
        }
    }

    /**
     * This happens when one of the games are clicked on.
     *
     * @param view View that is clicked.
     */
    public void openAddFriendFragment(View view) {

        AddFriendFragment addFriendFragment = AddFriendFragment.newInstance();

            // Get the FragmentManager and start a transaction.
            // A transaction wraps all the the Fragment operations together before the transaction is committed.
            FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.friend_fragment_container, addFriendFragment).addToBackStack(null);

            // Add the ChatFragment, added to backstack of fragment transactions, which allows the user to return
            // to the previous Fragment state pressed by the back button., calls commit for the transaction to take effect.
            fragmentTransaction.commit();




    }






}
