package remote;

public class RemoteShoppingListEntry {
    private String id;
    private String name;
    private int checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int isChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}
