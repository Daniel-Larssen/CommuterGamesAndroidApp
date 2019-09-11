package com.example.android.CommuterGames;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.android.CommuterGames.ui.login.LoginActivity;

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
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
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
        Intent gameListActivity = new Intent(this, gamelistActivity.class);
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
