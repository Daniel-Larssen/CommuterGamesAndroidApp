package com.example;

import android.app.Application;
import android.content.Context;

public class CommuterGames extends Application {

        // this class is used when non-fragments/activities need a context.
        // https://stackoverflow.com/questions/2002288/static-way-to-get-context-in-android

        private static Application sApplication;

        public static Application getApplication() {
            return sApplication;
        }

        public static Context getContext() {
            return getApplication().getApplicationContext();
        }

        @Override
        public void onCreate() {
            super.onCreate();
            sApplication = this;
        }
    }
