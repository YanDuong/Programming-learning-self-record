package com.example.freshfood;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App app;
    private App() {}
    @Override
    public void onCreate()  {
        super.onCreate();
        app = this;
    }


    public Context getContext() {
        return app.getApplicationContext();
    }
}
