package com.example.android.CommuterGames;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * This is the class that all other activities will extend to,
 * it will extend appCompatActivity and hold the shared methods
 *  like the creation of navigation items.
 */
public class Activity extends AppCompatActivity {

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

    // ------------------------------- Database -----------------------------------

    /**
     * Checks if there is a internet connection up. Needs to be
     * called from all of the pages where items from the database
     * is being collected.
     *
     * @return
     */
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    // Reads all the games on the database.
    public  void readData(StringRequest stringRequest) {
        if (isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        } else {
            Toast.makeText(this, "Ingen nettverkstilgang. Kan ikke laste varer.", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO finish the collection of data
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "volley feilet!", Toast.LENGTH_LONG).show();

    }

    // -------------------------------- // -----------------------------------------

    /**
     * Opens the Profile activity and is used in the navigation bar.
     */
    public void profileActivity() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
    }

    /**
     * Opens the Login activity and is used in the navigation bar.
     */
    public void loginActivity(View view) {
        //Intent loginIntent = new Intent(this, LoginActivity.class);
        //startActivity(loginIntent);
    }

    /**
     * Opens the Friend activity and is used in the navigation bar.
     */
    public void friendActivity() {
        Intent friendIntent = new Intent(this, FriendActivity.class);
        startActivity(friendIntent);
    }

    /**
     * Opens the Gamelist activity and is used in the navigation bar.
     */
    public void gameListActivity() {
        Intent gameListActivity = new Intent(this, GamelistActivity.class);
        startActivity(gameListActivity);
    }

    /**
     * Opens the Game activity and is used in the navigation bar.
     */
    public void openGameActivity (View view) {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }
}
