package com.example.instagramclone;

import android.app.Application;

import com.example.instagramclone.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        // initialization
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("xaINrrUOfXQhgb6MgbQbSFJSUypxtKGlZ53uPFpS")
                .clientKey("hwiBUp2SkqBBnKOoiokO9TflqTIzsyJmvWL8fLKU")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
