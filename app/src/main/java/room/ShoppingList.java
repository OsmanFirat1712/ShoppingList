package room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

        (tableName = "Lists") public class
ShoppingList {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "artikel")
    public String artikel;

    @ColumnInfo(name = "icon")
    public int icon;

    public ShoppingList(String artikel, int icon) {
        this.artikel = artikel;
        this.icon = icon;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getArtikel() {
        return artikel;
    }

    public void setArtikel(String artikel) {
        this.artikel = artikel;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
