package com.example.shoppinglistof;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SharedPrefdataStorage implements ListService {
    SharedPreferences sharedPref;

    public SharedPrefdataStorage(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    Gson gson = new Gson();
    @Override
    public void safeList(List<ShoppingList> shoppingLists) {
        SharedPreferences.Editor editor = sharedPref.edit();
        String StringJson = gson.toJson(shoppingLists);
        editor.putString("KEY", StringJson);
        editor.apply();
    }

    @Override
    public List<ShoppingList> getShoppingList(SortOrder sortOrder) {
        Type x = new TypeToken<ArrayList<ShoppingList>>() {
        }.getType();
        List<ShoppingList> createList = gson.fromJson(sharedPref.getString("KEY", "[]"), x);
        return createList;
    }

    @Nullable
    @Override
    public ShoppingList shoppingList(UUID listId) {
        return null;
    }

    @Override
    public void add(String name, int icon, int color) {
        List<ShoppingList> shoppingLists = getShoppingList(SortOrder.Alphabetical);
        shoppingLists.add(new ShoppingList(UUID.randomUUID(),name, icon, color, new ArrayList<ShoppingListEntry>(), new ArrayList<ShoppingListEntry>()));
        safeList(shoppingLists);
    }

    @Override
    public void remove(UUID listId) {

    }

    @Override
    public void addEntry(UUID listId, String name) {

    }

    @Override
    public void checkEntry(UUID listId, int row) {

    }

    @Override
    public void uncheckEntry(UUID listId, int row) {

    }

    @Override
    public void uncheckAllEntries(UUID listId) {

    }

    @Override
    public void changeName(UUID listId, String name) {

    }

    @Override
    public void changeIcon(UUID listId, int icon) {

    }

    @Override
    public void changeColor(UUID listId, int color) {

    }
}
