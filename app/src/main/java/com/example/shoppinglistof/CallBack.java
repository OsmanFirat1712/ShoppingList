package com.example.shoppinglistof;

import java.util.UUID;

public interface CallBack {
    public void getList(ShoppingList checkedUnchecked);


    public void remove(UUID uuid);

    void editlist(ShoppingList shoppingList);

}