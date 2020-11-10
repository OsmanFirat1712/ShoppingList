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
        ShoppingList shoppingList = null;
        List<ShoppingList> shoppingLists = getShoppingList(SortOrder.Alphabetical);
        for (int i = 0; i < shoppingLists.size(); i++) {
            if (listId.equals(shoppingLists.get(i).getId())) {
                shoppingList = shoppingLists.get(i);

            }
        }
        return shoppingList;
    }

    @Override
    public void add(String name, int icon, int color) {
        List<ShoppingList> shoppingLists = getShoppingList(SortOrder.Alphabetical);
        shoppingLists.add(new ShoppingList(UUID.randomUUID(), name, icon, color));
        safeList(shoppingLists);
    }

    @Override
    public void remove(UUID listId) {
        List<ShoppingList> allshoppingLists = getShoppingList(SortOrder.Alphabetical);
        for (int i = 0; i < allshoppingLists.size(); i++) {
            if (listId.equals(allshoppingLists.get(i).getId())) {
                allshoppingLists.remove(i);
                break;
            }
        }
        safeList(allshoppingLists);


    }

    @Override
    public void addEntry(UUID listId, String name) {

        List<ShoppingList> shoppingLists = getShoppingList(SortOrder.Alphabetical);
        for (int i = 0; i < shoppingLists.size(); i++) {
            if (listId.equals(shoppingLists.get(i).getId())) {
                shoppingLists.get(i).getUncheckedEntries().add(new ShoppingListEntry(listId, name, false));
            }


        }
        safeList(shoppingLists);
    }

    @Override
    public void checkEntry(UUID listId, int row) {
        List<ShoppingList> shoppingLists = getShoppingList(SortOrder.Alphabetical);
        for (int i = 0; i < shoppingLists.size(); i++) {
            if (listId.equals(shoppingLists.get(i).getId())) {
                ShoppingListEntry shop = shoppingLists.get(i).getUncheckedEntries().get(row);
                shoppingLists.get(i).getUncheckedEntries().remove(row);
                shop.setChecked(true);
                shoppingLists.get(i).getCheckedEntries().add(shop);
            }


        }
        safeList(shoppingLists);
    }

    @Override
    public void uncheckEntry(UUID listId, int row) {
        List<ShoppingList> shoppingLists = getShoppingList(SortOrder.Alphabetical);
        for (int i = 0; i < shoppingLists.size(); i++) {
            if (listId.equals(shoppingLists.get(i).getId())) {
                ShoppingListEntry shop = shoppingLists.get(i).getCheckedEntries().get(row - shoppingLists.get(i).getUncheckedEntries().size());
                shoppingLists.get(i).getCheckedEntries().remove(row - shoppingLists.get(i).getUncheckedEntries().size());
                shop.setChecked(false);
                shoppingLists.get(i).getUncheckedEntries().add(shop);

            }
        }
        safeList(shoppingLists);
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

    @Override
    public void removeEntry(UUID listId, UUID entryId) {
        List<ShoppingList> shoppingLists = getShoppingList(SortOrder.Alphabetical);
        for (int i = 0; i < shoppingLists.size(); i++) {
            if (listId.equals(shoppingLists.get(i).getId())) {
                for (int j = 0; j < shoppingLists.get(i).getCheckedEntries().size(); j++) {
                    if (entryId.equals(shoppingLists.get(i).getCheckedEntries().get(j).getId())) {
                        shoppingLists.get(i).getCheckedEntries().remove(j);
                    }
                }
                for (int j = 0; j < shoppingLists.get(i).getUncheckedEntries().size(); j++) {
                    if (entryId.equals(shoppingLists.get(i).getUncheckedEntries().get(j).getId())) {
                        shoppingLists.get(i).getUncheckedEntries().remove(j);
                    }
                }

            }
        }
        safeList(shoppingLists);
    }
}