package remote;

import java.util.List;

public class RemoteShoppingList {

    private String id;
    private String name;
    private String icon;
    private String color;
    private List<RemoteShoppingListEntry> shoppinglistentries;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<RemoteShoppingListEntry> getshoppinglistentries() {
        return shoppinglistentries;
    }

    public void setShoppinglistentries(List<RemoteShoppingListEntry> shoppinglistentries) {
        this.shoppinglistentries = shoppinglistentries;
    }
}
