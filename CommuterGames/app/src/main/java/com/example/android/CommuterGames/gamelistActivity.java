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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.android.CommuterGames.ui.login.LoginActivity;

import java.util.ArrayList;

/**
 * Main Activity for the Material Me app, a mock sports news application.
 */
public class gamelistActivity extends Activity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Game> mSportsData;
    private GamesAdapter mAdapter;

    // Temp needed to let the icon change on list_size work, To >Be >fixed.
    private boolean tempToolItem = true;


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

}
