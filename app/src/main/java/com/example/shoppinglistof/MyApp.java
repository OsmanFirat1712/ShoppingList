package com.example.shoppinglistof;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import timber.log.Timber;

public class MyApp extends Application {
    private SharedPreferences sharedPreferences;

    private ListService listService;


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        listService = new SharedPrefdataStorage(sharedPreferences);
        Timber.plant(new Timber.DebugTree());

    }

    public ListService getListService() {
        return listService;
    }
}
