package com.example.shoppinglistof;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ShoppingListsChecked {
    @Embedded public ShoppingList shoppingList;
    @Relation(
            parentColumn = "shoppinglistid",
            entityColumn = "shoppinglistentryId"
    )
    public List<ShoppingListEntry> checkedEntries;
}
