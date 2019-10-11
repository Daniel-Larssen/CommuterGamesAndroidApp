package com.example.android.CommuterGames;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * The activity where the games is placed.
 */
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Adds the items to the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        // Place the up action on the toolbar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // TODO Collect the data about the game with getExtra, when its needed.

        // Sets up the WebView for the html-file where the javascript game is placed.
        // TODO: Collect the game from the database.
        WebView web = findViewById(R.id.webview);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("file:///android_asset/game1.html");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // If the favorite button is clicked.
        if (id == R.id.favorite) {
            // Inside here we will need
            if (item.getTitle().toString() == "Yes") {
                item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_non_smile));
                item.setTitle("No");
            } else {
                item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_smile));
                item.setTitle("Yes");
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
