package com.example.android.CommuterGames;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.CommuterGames.User.User;
import com.example.android.CommuterGames.data.LoginRepository;
import com.example.android.CommuterGames.data.ui.login.LoginActivity;

import org.json.JSONObject;

/**
 * This is the class that all other activities will extend to,
 * it will extend appCompatActivity and hold the shared methods
 * like the creation of navigation items.
 *
 * Her ligger CRUD metodene som tas i bruk,
 */
public class Activity extends AppCompatActivity {

    // Is the ENDPOINT where the api is called, is collected and used in every activity that uses the database.
    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";

    public static RequestQueue queue;

    // Bottom Navigation Item Listener
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    profileActivity();
                    return true;
                case R.id.navigation_dashboard:
                    gameListActivity();
                    return true;
                case R.id.navigation_notifications:
                    friendActivity();
                    return true;
            }
            return false;
        }
    };

    // ------------------------------- Database methods -----------------------------------

    /**
     * Checks if there is a internet connection up. Needs to be
     * called from all of the pages where items from the database
     * is being collected.
     *
     * @return true if the database is reached, false if bot
     */
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "volley feilet!", Toast.LENGTH_LONG).show();

    }

    /**
     * The method that attaches the correct string at the end
     * of ENDPOINT when something needs to be inserted into
     * the database, Not
     *
     * @param object
     */
    public void createDbObject(User object) {
        String URL = ENDPOINT + "/users";
        JSONObject jsonVare = object.getJSONObject();
        addJSONRequest(Request.Method.POST, URL, jsonVare);
    }

    /**
     * The method that attaches the correct string at the end
     * of ENDPOINT when something needs to be updated in
     * the database.
     *
     * @param object
     */
    public void updateDbObject(int userId, String table, User object) {
        String URL = ENDPOINT + "/" + table + "/" + userId;
        JSONObject jsonObject = object.getJSONObject();
        addJSONRequest(Request.Method.PUT, URL, jsonObject);
    }

    /**
     * The method that attaches the correct string at the end
     * of ENDPOINT when something needs to be deletet in
     * the database.
     *
     * @param objectId The id of the object you want to remove
     */
    public void deleteDbObject(int objectId, String table) {
        String URL = ENDPOINT + "/u" + table + "/" + objectId;
        addJSONRequest(Request.Method.DELETE, URL, null);
    }

    /**
     * Collects all of the rows in a table, adds the
     * StringRequest to the queue.
     *
     * @param stringRequest
     */
    public void readDbObject(StringRequest stringRequest) {
        if (isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        } else {
            Toast.makeText(this, "Ingen nettverkstilgang. Kan ikke laste inn fra databasen.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sends the JSONRequest to the queue.
     *
     * @param RequestMethod
     * @param url
     * @param data
     */
    public void addJSONRequest(int RequestMethod, String url, JSONObject data) {
        if (isOnline()) {
            queue = Volley.newRequestQueue(this.getApplicationContext());
            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(RequestMethod, url, data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getBaseContext(), "Your User-information have been updated.", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(getBaseContext(), "An error happened when you tried to update your information.", Toast.LENGTH_LONG).show();
                            // TODO An error happens, but the update works?
                        }
                    });
            queue.add(jsonObjRequest);
        }
    }

    // -------------------------- Used in the bottom navigation-bar -----------------------------------

    /**
     * Opens the Login activity and is used in the navigation bar.
     * If there is a user signed in, then you are sent to the profile,
     * if not you are sent to the login.
     */
    public void profileActivity() {

        if (!LoginRepository.isLoggedIn()) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } else {
            Intent profileIntent = new Intent(this, ProfileActivity.class);
            startActivity(profileIntent);
        }
    }

    /**
     * Opens the Friend activity and is used in the navigation bar.
     * If there is a user signed in, then you are sent to the friendlist,
     * if not you are sent to the login.
     */
    public void friendActivity() {
        if (!LoginRepository.isLoggedIn()) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } else {
            Intent friendIntent = new Intent(this, FriendActivity.class);
            startActivity(friendIntent);
        }
    }

    /**
     * Opens the GameList activity and is used in the navigation bar.
     */
    public void gameListActivity() {
        Intent gameListActivity = new Intent(this, GameListActivity.class);
        startActivity(gameListActivity);
    }

    /**
     * Opens the Game activity and is used in the navigation bar.
     */
    public void openGameActivity(View view) {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }

}
