package room;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.shoppinglistof.ApiListService;
import com.example.shoppinglistof.CompletionCallBack;
import com.example.shoppinglistof.GetShoppingListCallBack;
import com.example.shoppinglistof.GetShoppingListsCallBack;
import com.example.shoppinglistof.ListSave;
import com.example.shoppinglistof.ListService;
import com.example.shoppinglistof.ShoppingList;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.internal.concurrent.Task;

public  class DaoListService implements ApiListService {
    private DataBase dataBase;
    private TaskRunner taskRunner = new TaskRunner();
    private List<ShoppingList> listshoppingList;
    private ShoppingList shoppingList;
    private room.ShoppingList roomShoppingList;
    Context context;

    public DaoListService(DataBase dataBase, Context context, TaskRunner taskRunner) {
        this.dataBase = dataBase;
        this.context = context;
    }


    @Override
    public void getShoppingList(SortOrder sortOrder, GetShoppingListsCallBack callBack) {
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(() -> {
            List<ShoppingList> shoppingLists1 = dataBase.shoppingDao().getAll();

            return shoppingLists1;
        }, (result) -> {
            callBack.onShopppingListsLoaded(result);
        });
    }



    @Nullable
    @Override
    public void ShoppingList(UUID listId, CompletionCallBack completionCallBack) {
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(() -> {
            ShoppingList singleShoppingList = dataBase.shoppingDao().loadOneShoppingList(listId);

            return singleShoppingList;
        }, (result) -> {
            completionCallBack.onComplete();
        });
    }


    @Override
    public void safeList(List<ShoppingList> shoppingLists) {

    }

    @Override
    public void add(String name, int icon, int color, ListSave listSave) {
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(() -> {
            ShoppingList shoppingList= new ShoppingList(UUID.randomUUID(),name, icon,color);
            dataBase.shoppingDao().add(shoppingList);

            return shoppingList;
        }, (result) -> {
        listSave.listToSave();
        });
    }

    @Override
    public void remove(UUID listId, CompletionCallBack completionCallBack) {
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(() -> {
            dataBase.shoppingDao().delete(shoppingList);

            return shoppingList;
        }, (result) -> {
            completionCallBack.onComplete();
        });
    }
    @Override
    public void addEntry(UUID listId, String name, CompletionCallBack completionCallBack) {

    }

    @Override
    public void checkEntry(UUID listId, UUID entryId, CompletionCallBack completionCallBack) {

    }

    @Override
    public void uncheckEntry(UUID listId, UUID entryId, CompletionCallBack completionCallBack) {

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
    public void removeEntry(UUID listId, UUID entryId, CompletionCallBack completionCallBack) {

    }
}