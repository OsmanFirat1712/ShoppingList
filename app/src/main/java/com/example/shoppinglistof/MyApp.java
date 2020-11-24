package com.example.shoppinglistof;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import remote.RemoteShoppingList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MyApp extends Application {
    private SharedPreferences sharedPreferences;

    private ListService listService;
    private ApiListService apiListService;
    private RemoteShoppingList remoteShoppingList;



    ShoppingListApi shoppingListApi = null;
    private static String BASE_URL = "https://codingschoolshoppinglist.dev.webundsoehne.com/api/v1/";

    private Gson gson;


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        listService = new SharedPrefdataStorage(sharedPreferences);
        apiListService = new ApiClass(getShoppingListApi());
        Timber.plant(new Timber.DebugTree());
/*
        gson = new GsonBuilder()
                .create();*/
    }

    public ApiListService getApiListService() {
        return apiListService;
    }

    public ListService getListService() {
        return listService;
    }

    public ShoppingListApi getShoppingListApi() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();


        if  ( shoppingListApi  == null){
            shoppingListApi  = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ShoppingListApi.class);

        }

        return shoppingListApi;
    }
}

