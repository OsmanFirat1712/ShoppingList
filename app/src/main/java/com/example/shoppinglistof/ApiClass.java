package com.example.shoppinglistof;

import android.graphics.Color;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import remote.RemoteShoppingList;
import remote.RemoteShoppingListEntry;
import remote.UpdateShoppingListEntry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiClass implements ApiListService {

    ApiClass apiClass;
    Gson gson;
    ShoppingListApi shoppingListApi;





    public ApiClass(ApiClass apiClass,ShoppingListApi shoppingListApi) {
        this.apiClass = apiClass;



    }

    public ApiClass(ShoppingListApi shoppingListApi) {
        this.shoppingListApi = shoppingListApi;
        gson = new GsonBuilder()
                .create();
    }


    @Override
    public void getShoppingList(SortOrder sortOrder, final GetShoppingListsCallBack callBack) {
        shoppingListApi.getShoppingLists().enqueue(new Callback<List<RemoteShoppingList>>() {
            @Override
            public void onResponse(Call<List<RemoteShoppingList>> call, Response<List<RemoteShoppingList>> response) {
                List<ShoppingList> mappedList = new ArrayList<>();
                for (RemoteShoppingList remoteItem : response.body()) {

                    List<ShoppingListEntry> checkedEntries = new ArrayList<>();
                    List<ShoppingListEntry> uncheckedEntries = new ArrayList<>();

                    for (RemoteShoppingListEntry remoteEntry : remoteItem.getshoppinglistentries()) {
                        if (remoteEntry.isChecked() == 1) {
                            checkedEntries.add(new ShoppingListEntry(
                                    UUID.fromString(remoteEntry.getId()),
                                    remoteEntry.getName(),

                                    true
                            ));
                        } else {
                            uncheckedEntries.add(new ShoppingListEntry(
                                    UUID.fromString(remoteEntry.getId()),
                                    remoteEntry.getName(),
                                    false
                            ));
                        }
                    }

                        int icon;
                        switch (remoteItem.getIcon()) {
                            case "icon1":
                                icon = R.drawable.basket_24;
                                break;
                            case "icon2":
                                icon = R.drawable.book_24;
                                break;
                            case "icon3":
                                icon = R.drawable.store_24;
                                break;



                            default:
                                icon = R.drawable.basket_24;

                        }
                        int color = Color.parseColor("#a4c639");
                        try {
                            color = Color.parseColor(remoteItem.getColor());

                        } catch (IllegalArgumentException e) {
                            color = Color.parseColor("#a4c639");

                        }


                        mappedList.add(new ShoppingList(
                                UUID.fromString(remoteItem.getId()),
                                remoteItem.getName(),
                                icon,
                                color,
                                checkedEntries,
                                uncheckedEntries

                        ));

                    }


                    callBack.onShopppingListsLoaded(mappedList);

                }

            @Override
            public void onFailure(Call<List<RemoteShoppingList>> call, Throwable t) {

            }


        });
    }


    @Nullable
    @Override
    public void ShoppingList(UUID listId, CompletionCallBack completionCallBack) {
        retrofit2.Call<RemoteShoppingList> getShoppingList = shoppingListApi.getShoppingList(listId.toString());
        getShoppingList.enqueue(new Callback<RemoteShoppingList>() {
            @Override
            public void onResponse(Call<RemoteShoppingList> call, Response<RemoteShoppingList> response) {
                RemoteShoppingList remoteItem = response.body();

                List<ShoppingListEntry> checkedEntries = new ArrayList<>();
                List<ShoppingListEntry> uncheckedEntries = new ArrayList<>();


                for (RemoteShoppingListEntry remoteEntry : remoteItem.getshoppinglistentries()) {
                    if (remoteEntry.isChecked() == 1) {
                        checkedEntries.add(new ShoppingListEntry(
                                UUID.fromString(remoteEntry.getId()),
                                remoteEntry.getName(),
                                true
                        ));
                    } else {
                        uncheckedEntries.add(new ShoppingListEntry(
                                UUID.fromString(remoteEntry.getId()),
                                remoteEntry.getName(),
                                false

                        ));
                    }
                }
                    int icon;
                    switch (remoteItem.getIcon()) {
                        case "icon1":
                            icon = R.drawable.basket_24;
                            break;
                        case "icon2":
                            icon = R.drawable.book_24;
                            break;
                        case "icon3":
                            icon = R.drawable.book_24;
                            break;



                        default:
                            icon = R.drawable.basket_24;

                    }
                    int color= Color.parseColor("#FF2000");
                    try {
                        color = Color.parseColor(remoteItem.getColor());

                    }catch (IllegalArgumentException e){
                        color = Color.parseColor("#a4c639");

                    }
                    new ShoppingList(
                            UUID.fromString(remoteItem.getId()),
                            remoteItem.getName(),
                            icon,
                            color,
                            checkedEntries,
                            uncheckedEntries

                    );

                            completionCallBack.onComplete();

                }

            @Override
            public void onFailure(Call<RemoteShoppingList> call, Throwable t) {

            }
        });
    }

    @Override
    public void safeList(List<ShoppingList> shoppingLists) {

    }

    @Override
    public void add(String name, int icon, int color, ListSave listSave) {
        Call<ResponseBody> call = shoppingListApi.addShoppingList(new ShoppingList(UUID.randomUUID(), name, icon, color));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    listSave.listToSave();
                }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void remove(UUID listId, CompletionCallBack completionCallBack) {
        shoppingListApi.deleteShoppingList(listId.toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                completionCallBack.onComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    @Override
    public void addEntry(UUID listId, String name, CompletionCallBack completionCallBack) {
        shoppingListApi.addShoppinglistEntry(listId.toString(),new RemoteShoppingListEntry()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                completionCallBack.onComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
            }
        });
    }

    @Override
    public void checkEntry(UUID listId, UUID entryId,CompletionCallBack completionCallBack) {
        shoppingListApi.editShoppingListEntry(listId.toString(),entryId.toString(),new UpdateShoppingListEntry(true)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                completionCallBack.onComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    @Override
    public void uncheckEntry(UUID listId, UUID entryId, CompletionCallBack completionCallBack) {
        shoppingListApi.editShoppingListEntry(listId.toString(),entryId.toString(),new UpdateShoppingListEntry(false)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                completionCallBack.onComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
    public void removeEntry(UUID listId, UUID entryId,CompletionCallBack completionCallBack) {
        shoppingListApi.deleteShoppingListEntry(listId.toString(),entryId.toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                completionCallBack.onComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
