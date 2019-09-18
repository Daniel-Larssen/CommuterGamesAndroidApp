/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.CommuterGames;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
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
 * Main Activity for the Material Me app, a mock sports news application.
 */
public class gamelistActivity extends Activity implements Response.Listener<String>, Response.ErrorListener {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Game> mSportsData;
    private GamesAdapter mAdapter;

    // Temp needed to let the icon change on list_size work, To >Be >fixed.
    private boolean tempToolItem = true;

    private ArrayList<Game> gameArrayList = new ArrayList<Game>();
    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";
    public final static String gameliste_URL = ENDPOINT + "/games?transform=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);

        // Adds the items to the toolbar.
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Adds the bottom navigation.
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_dashboard);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        readAllGames();

        // If the game-fragment is set up already.
        if (findViewById(R.id.game_fragment_container) != null) {

            // To not end up with overlapping fragments at return from friendchat
            if (savedInstanceState != null) {
                return;
            }

            // Sets up the game-list fragment and attaches it to game_fragment_container.
            GameListFragment gameListFragment = new GameListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.game_fragment_container, gameListFragment).commit();
        }
    }

    // TODO finish the collection of data
    private  void readAllGames() {
        if (isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, gameliste_URL, this, this);
            queue.add(stringRequest);
        } else {
            Toast.makeText(this, "Ingen nettverkstilgang. Kan ikke laste varer.", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO finish the collection of data
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    // TODO finish the collection of data
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "volley feilet!", Toast.LENGTH_LONG).show();

    }

    // TODO finish the collection of data
    @Override
    public void onResponse(String response) {
        try {
            gameArrayList = Game.lagGameListe(response);
            oppdaterVareListView(gameArrayList);
            Toast.makeText(this, "Is ReAD.", Toast.LENGTH_LONG).show();
        }catch (JSONException e) {
            Toast.makeText(this, "Ugyldig JSON-data.", Toast.LENGTH_LONG).show();
        }
    }

    // TODO finish the collection of data
    private void oppdaterVareListView(ArrayList<Game> nyVareListe) {

        Game gameOne = nyVareListe.get(1);
        Toast.makeText(this, gameOne.getTitle(), Toast.LENGTH_LONG).show();
        //mAdapter = new GamesAdapter(this, gameArrayList);
        //mRecyclerView.setAdapter(mAdapter);
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
