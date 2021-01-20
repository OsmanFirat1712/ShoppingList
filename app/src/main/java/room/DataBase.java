package room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.shoppinglistof.ShoppingList;


@Database(entities = {ShoppingList.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DataBase extends RoomDatabase {

        private static DataBase instance;

    public abstract DAO shoppingDao();

    public static synchronized DataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
