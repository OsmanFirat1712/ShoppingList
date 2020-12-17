package com.example.shoppinglistof;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import remote.RemoteShoppingList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import room.DAO;
import room.DaoListInterface;
import room.DaoListService;
import room.DataBase;
import room.ShoppingList;
import timber.log.Timber;

public class MyApp extends Application {
    private SharedPreferences sharedPreferences;

    private ListService listService;
    private ApiListService apiListService;
    private RemoteShoppingList remoteShoppingList;
    private DaoListInterface daoListInterface;


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


    public DaoListInterface getDaoListInterface() {
        return daoListInterface;
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


        if (shoppingListApi == null) {
            shoppingListApi = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build()
                    .create(ShoppingListApi.class);

        }

        return shoppingListApi;
    }
}

