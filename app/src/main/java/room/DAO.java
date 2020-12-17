package room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

@Dao
public interface DAO {



    @Query("SELECT * FROM ShoppingList")
    List<com.example.shoppinglistof.ShoppingList> getAll();


    @Query("SELECT * FROM ShoppingList WHERE  :listId=id")
    com.example.shoppinglistof.ShoppingList loadOneShoppingList(UUID listId);

    @Insert
    void add(com.example.shoppinglistof.ShoppingList... shoppingLists);

    @Delete
    void delete(com.example.shoppinglistof.ShoppingList listId);

/*    @Update
    void update(ShoppingList shoppingList);*/

}