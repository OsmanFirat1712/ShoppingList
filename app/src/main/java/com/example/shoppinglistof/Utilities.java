package com.example.shoppinglistof;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

    // Needs the dependency:
// implementation 'com.google.code.gson:gson:2.8.6'
    public class Utilities {

        public static String toString(com.example.shoppinglistof.ShoppingList user) {
            return new Gson().toJson(user);
        }

        public static com.example.shoppinglistof.ShoppingList userFromString(String user) {
            return new Gson().fromJson(user, com.example.shoppinglistof.ShoppingList.class);
        }

        public static String listToString(List<com.example.shoppinglistof.ShoppingList> userList) {
            return new Gson().toJson(userList);
        }

        public static List<com.example.shoppinglistof.ShoppingList> listFromString(String userList) {
            return new Gson().fromJson(userList, new TypeToken<ArrayList<com.example.shoppinglistof.ShoppingList>>(){}.getType());
        }

    }
