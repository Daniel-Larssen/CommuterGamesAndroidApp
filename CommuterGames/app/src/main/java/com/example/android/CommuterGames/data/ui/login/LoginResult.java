/**
 * This part of the code, and the rest of the classes inside
 * of the data-package, was mostly taken from the Login Activity
 * template, and only has a few changes made, mainly in
 * LoginDataSource.
 */

package com.example.android.CommuterGames.data.ui.login;

import android.support.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
