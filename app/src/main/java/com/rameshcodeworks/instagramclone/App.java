package com.rameshcodeworks.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("XzUtEEu46TFGUDUdLGvQwaqyjWzCH9kRzYYqymSa")
                // if defined
                .clientKey("XfYWIjuMAOR6P4EN54PIxE4vmpAQ3Mx3mrnC1dcF")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
