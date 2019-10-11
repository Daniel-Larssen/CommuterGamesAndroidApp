/**
 * This part of the code, and the rest of the classes inside
 * of the data-package, was mostly taken from the Login Activity
 * template, and only has a few changes made, mainly in
 * LoginDataSource.
 */

package com.example.android.CommuterGames.data;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.CommuterGames;
import com.example.android.CommuterGames.User.User;
import com.example.android.CommuterGames.data.model.LoggedInUser;

import org.json.JSONException;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public final static String ENDPOINT = "https://itfag.usn.no/~194535/api.php";
    public static RequestQueue queue;
    LoggedInUser loggedInUser;

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // This is where the check for where the user is looked for in the database.
            String userCheck_URL = ENDPOINT + "/users?filter=password,eq," + password + "&filter=email,eq," + username + "&transform=1";

            queue = Volley.newRequestQueue(CommuterGames.getContext());

            loggedInUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "");

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, userCheck_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        User user = User.lagUser(response);
                        Toast.makeText(CommuterGames.getContext(),"Welcome " + user.getFullName(), Toast.LENGTH_LONG).show();

                        loggedInUser = new LoggedInUser("" + user.getId(),user.getUsername());
                        LoginRepository.user = loggedInUser;


                    } catch (JSONException e) {
                        Toast.makeText(CommuterGames.getContext(), "Error", Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CommuterGames.getContext(), "A error happened with the StringRequest.", Toast.LENGTH_LONG).show();
                }
            });

            queue.add(stringRequest);

            return new Result.Success<>(loggedInUser);

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
