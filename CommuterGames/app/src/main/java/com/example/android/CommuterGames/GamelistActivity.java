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

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.util.ArrayList;

public class GamelistActivity extends Activity implements Response.Listener<String>, Response.ErrorListener {

    public static ArrayList<Game> gameArrayList = new ArrayList<Game>();
    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";

    // Collects all of the games on the database.
    public final static String gameliste_URL = ENDPOINT + "/games?transform=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);

        // Adds the bottom navigation.
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_dashboard);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Collects all the games into the list, also created the new fragment in onResponse.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, gameliste_URL, this, this);
        readData(stringRequest);

    }


    // TODO finish the collection of data
    @Override
    public void onResponse(String response) {

        try {
            gameArrayList = Game.lagGameListe(response);
            oppdaterVareListView(gameArrayList);
        }catch (JSONException e) {
            Toast.makeText(this, "Ugyldig JSON-data.", Toast.LENGTH_LONG).show();
        }

        // If the game-fragment is set up already.
        if (findViewById(R.id.game_fragment_container) != null) {

            // Sets up the game-list fragment and attaches it to game_fragment_container.
            GameListFragment gameListFragment = new GameListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.game_fragment_container, gameListFragment).commit();
        }
    }

    // TODO finish the collection of data
    private void oppdaterVareListView(ArrayList<Game> nyVareListe) {

        //mAdapter = new GamesAdapter(this, gameArrayList);
        //mRecyclerView.setAdapter(mAdapter);
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
