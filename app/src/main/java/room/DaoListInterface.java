package room;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.example.shoppinglistof.ListService;
import com.example.shoppinglistof.ShoppingList;

import java.util.List;
import java.util.UUID;

public interface DaoListInterface {
    enum SortOrder {
        Alphabetical,
        UncheckedCount

}

    /**
     * @param sortOrder The desired order
     * @return All shopping lists sorted by sortOrder.
     */
    List<ShoppingList> getShoppingList(ListService.SortOrder sortOrder,GetShoppingListsCallBack getShoppingListsCallBack);

    /**
     * Returns a shopping list with the given `listId`.
     *
     * @param listId The id of the desired list.
     * @return The shopping list with the given `listId` if it exists, otherwise null.
     */
    @Nullable
    com.example.shoppinglistof.ShoppingList shoppingList(UUID listId);


    public void safeList(List<ShoppingList> shoppingLists);

    /**
     * Creates and adds a new shopping list.
     * <p>
     * This function does nothing if `name` is empty.
     *
     * @param name  The name for the new list.
     * @param icon  The icon for the new list.
     * @param color The color for the new list.
     */
    void add(String name, @DrawableRes int icon, @ColorInt int color);

    /**
     * Removes the shopping list.
     * <p>
     * This function does nothing if `listId` is invalid
     *
     * @param listId The id of the list that should be removed.
     */
    void remove(UUID listId);

    /**
     * Saves the shopping list.
     * <p>
     * This function does nothing if `listId` is invalid or `name` is empty.
     *
     * @param listId The id of the list that should have the entry added.
     * @param name   The name of the entry.
     */
    void addEntry(UUID listId, String name);

    /**
     * Checks an entry in a list.
     * <p>
     * This function does nothing if `listId` or `row` is invalid.
     *
     * @param listId The list that should have its entry checked.
     * @param row    The row of the item to check.
     */
    void checkEntry(UUID listId, int row);

    /**
     * Unchecks an entry in a list.
     * <p>
     * This function does nothing if `listId` or `row` is invalid.
     *
     * @param listId The id of the list that should have its entry unchecked.
     * @param row    The row of the item to uncheck.
     */
    void uncheckEntry(UUID listId, int row);

    /**
     * Unchecks all entries in a list.
     * <p>
     * This function does nothing if `listId` is invalid.
     *
     * @param listId The id of the list that should have its entries unchecked.
     */
    void uncheckAllEntries(UUID listId);

    /**
     * Changes the name of a list.
     * <p>
     * This function does nothing if `listId` is invalid or `name` is empty.
     *
     * @param listId The id of the list that should have its name changed.
     * @param name   The new name.
     */
    void changeName(UUID listId, String name);


    void changeIcon(UUID listId, @DrawableRes int icon);

    /**
     * Changes the color of a list.
     * <p>
     * This function does nothing if `listId` is invalid.
     *
     * @param listId The id of the list that should have its icon changed.
     * @param color  The new icon.
     */
    void changeColor(UUID listId, @ColorInt int color);

    void removeEntry(UUID listId, UUID entryId);


    /**
     * Unchecks all entries in a list.
     * <p>
     * This function does nothing if `listId` is invalid.
     *
     * @param listId The id of the list that should have its entries unchecked.
     */


}
interface GetShoppingListsCallBack {
    void onShopppingListsLoaded ( List<ShoppingList> shoppingLists);
}