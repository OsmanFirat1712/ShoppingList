package com.example.shoppinglistof;

public interface BackCall {
    public void addButton();

    public void addEntry(ShoppingListEntry shoppingListEntry, int position);

    public void removeEntry(ShoppingListEntry shoppingListEntry);


}
