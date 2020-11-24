package remote;

import androidx.annotation.Nullable;

public class UpdateShoppingListEntry {

    @Nullable
    private final String name;
    @Nullable
    private final Boolean checked;

    public UpdateShoppingListEntry(String name) {
        this.name = name;
        this.checked = null;
    }

    public UpdateShoppingListEntry(Boolean checked) {
        this.name = null;
        this.checked = checked;
    }


    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public Boolean getChecked() {
        return checked;
    }
}
