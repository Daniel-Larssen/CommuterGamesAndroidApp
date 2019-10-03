package com.example.android.CommuterGames;

import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends Activity implements Response.Listener<String>, Response.ErrorListener {

    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";
    public static RequestQueue queue;

    EditText firstNameEditText;
    EditText middleNameEditText;
    EditText lastNameEditText;

    TextView username;

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

        // TODO Needs to be the session-id, collect from where it is stored.
        String loggedInUser = ENDPOINT + "/users?filter=id,eq," + 2 + "&transform=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loggedInUser, this, this);
        readData(stringRequest);

    }

    /**
     * Called when the update button is clicked on, updates the
     * relevant row in the database is updated with the
     * updateUser(int, User) method.
     *
     * @param view
     */
    public void updateUser(View view) {
        // Creates a user object and collects the text from the TextViews.
        User user = new User();
        user.setId(2);
        user.setFirstname(firstNameEditText.getText().toString());
        user.setMiddlename(middleNameEditText.getText().toString());
        user.setLastname(lastNameEditText.getText().toString());

        // Calls on the method that updates the user.
        updateUser(user.getId(), "users", user);

        String loggedInUser = ENDPOINT + "/users?filter=id,eq," + user.getId() + "&transform=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loggedInUser, this, this);
        readData(stringRequest);
    }


    /**
     * Collects the data about the signed in user from the database, and places it in the elements
     * where it is supposed to be placed.
     *
     * @param response
     */
    @Override
    public void onResponse(String response) {
        try {
            User loggedInUser = User.lagUser(response);

            // Sets the name of the user next to the image.
            username.setText(loggedInUser.getFullName());
            firstNameEditText.setText(loggedInUser.getFirstname());
            middleNameEditText.setText(loggedInUser.getMiddlename());
            lastNameEditText.setText(loggedInUser.getLastname());
            Toast.makeText(this, loggedInUser.getFullName(), Toast.LENGTH_LONG).show();

        }catch (JSONException e) {
            Toast.makeText(this, "Ugyldig JSON-data.", Toast.LENGTH_LONG).show();
        }
    }


    // TODO Place these in  their own class and make them usable with all types of objects.


    /**
     * The method that attaches the correct string at the end
     * of ENDPOINT when something needs to be inserted into
     * the database.
     *
     * @param object
     */
    public void insertVare(User object) {
        String vare_URL = ENDPOINT + "/users";
        JSONObject jsonVare = object.getJSONObject();
        addJSONRequest(Request.Method.POST, vare_URL, jsonVare);
    }

    /**
     * The method that attaches the correct string at the end
     * of ENDPOINT when something needs to be updated in
     * the database.
     *
     * @param object
     *
     */
    public void updateUser(int userId, String table, User object) {
        String URL = ENDPOINT + "/" + table + "/" + userId;
        JSONObject jsonObject = object.getJSONObject();
        addJSONRequest(Request.Method.PUT, URL, jsonObject);
    }

    /**
     * The method that attaches the correct string at the end
     * of ENDPOINT when something needs to be deletet in
     * the database.
     *
     * @param userId
     */
    public void deleteVare(int userId) {
        String vare_URL = ENDPOINT + "/users/" + userId;
        addJSONRequest(Request.Method.DELETE, vare_URL, null);
    }

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
}


