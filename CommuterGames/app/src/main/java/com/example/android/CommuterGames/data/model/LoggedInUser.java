/**
 * This part of the code, and the rest of the classes inside
 * of the data-package, was mostly taken from the Login Activity
 * template, and only has a few changes made, mainly in
 * LoginDataSource.
 */

package com.example.android.CommuterGames.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
