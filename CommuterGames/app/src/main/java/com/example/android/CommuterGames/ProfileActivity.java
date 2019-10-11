package com.example.android.CommuterGames;

import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.android.CommuterGames.User.User;
import com.example.android.CommuterGames.User.UserController;
import com.example.android.CommuterGames.User.UserView;
import com.example.android.CommuterGames.data.LoginRepository;

import org.json.JSONException;

public class ProfileActivity extends Activity implements Response.Listener<String>, Response.ErrorListener {

    EditText firstNameEditText;
    EditText middleNameEditText;
    EditText lastNameEditText;
    TextView username;

    Boolean firstTime=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Adds the bottom navigation.
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_home);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        firstNameEditText = findViewById(R.id.firstNameEdit);
        middleNameEditText = findViewById(R.id.middleNameEdit);
        lastNameEditText = findViewById(R.id.lastNameEdit);
        username = findViewById(R.id.username);

        // Collects the logged in users id, and sends uses it to collect the data from the database-row with the id.
        String loggedInUser = ENDPOINT + "/users?filter=id,eq," + LoginRepository.user.getUserId() + "&transform=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loggedInUser, this, this);
        readDbObject(stringRequest);

    }

    /**
     * Called when the update button is clicked on, updates the
     * relevant row in the database is updated with the
     * updateDbObject(int, User) method.
     *
     * @param view
     */
    public void updateDbObject(View view) {

        // Creates a user object and collects the text from the TextViews.
        User user = new User();
        user.setFirstname(firstNameEditText.getText().toString());
        user.setMiddlename(middleNameEditText.getText().toString());
        user.setLastname(lastNameEditText.getText().toString());

        // Calls on the method that updates the user.
        updateDbObject(user.getId(), "users", user);

        // Collects the logged in users id, and sends uses it to collect the data from the database-row with the id.
        String loggedInUser = ENDPOINT + "/users?filter=id,eq," + LoginRepository.user.getUserId() + "&transform=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loggedInUser, this, this);
        readDbObject(stringRequest);

    }

    /**
     * Collects the data about the signed in user from the database, and places it
     * in the element where it is supposed to be placed.
     *
     * @param response
     */
    @Override
    public void onResponse(String response) {
        try {

            // Creates a user-controller after a user is created
            User loggedInUser = User.lagUser(response);
            UserView userView = new UserView();
            UserController controller = new UserController(loggedInUser, userView);

            // Sets the name of the user next to the image.
            username.setText(controller.getUserFullName());
            if (firstTime) {
                firstNameEditText.setText(controller.getUserFirstname());
                middleNameEditText.setText(controller.getUserMiddlename());
                lastNameEditText.setText(controller.getUserLastname());
                firstTime = false;
            }

        }catch (JSONException e) {
            Toast.makeText(this, "Ugyldig JSON-data.", Toast.LENGTH_LONG).show();
        }
    }

}


