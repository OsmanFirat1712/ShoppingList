package com.example.shoppinglistof;

import java.util.List;

import okhttp3.ResponseBody;
import remote.RemoteShoppingList;
import remote.RemoteShoppingListEntry;
import remote.UpdateShoppingListEntry;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShoppingListApi {

    @GET("shoppinglists")
    Call<List<RemoteShoppingList>> getShoppingLists();

    @POST("shoppinglists")
    Call<ResponseBody> addShoppingList(@Body ShoppingList shoppingList);

    @GET("/api/v1/shoppinglists/{shoppinglist}")
    Call<RemoteShoppingList> getShoppingList(@Path("shoppinglist ")String listId);

    @PUT("shoppinglists/{id}")
    Call<ResponseBody> editShoppingList (@Path("id") String id,@Body ShoppingList shoppingList);

    @DELETE("/api/v1/shoppinglists/{shoppinglist}")
    Call<ResponseBody> deleteShoppingList(@Path("shopppinglist ")String listId);



                                //    Entries

    @GET("shoppinglists/{id} /entries")
    Call<List<ShoppingListEntry>> getShoppingListEntries(@Path("id")String id);

    @POST("shoppinglists/{id} /entries")
    Call <ResponseBody> addShoppinglistEntry ( @Path("id") String id,@Body RemoteShoppingListEntry shoppingListEntry);

    @GET("shoppinglists/{id}/entries/{entryid}")
    Call<ShoppingListEntry> getShoppingListEntry(@Path("id") String id, @Path("entryId") String entryId);

    @PUT("shoppinglists/{id}/entries/{entryid}")
    Call<ResponseBody> editShoppingListEntry(@Path("id") String id, @Path("entryId") String entryId, @Body UpdateShoppingListEntry updateShoppingListEntry);

    @DELETE("shoppinglists/{id}/entries/{entryid}")
    Call<ResponseBody> deleteShoppingListEntry(@Path("id") String id, @Path("entryId") String entryId);




}
