package Model;

public abstract class MenuItem {
    private String name;

    /**
     * Creates a menu item with a given name.
     *
     * @param name
     * @author Rei
     */
    public MenuItem(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the menu item.
     *
     * @return name of the item
     * @author Rei
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the menu item.
     * Must be implemented by subclasses.
     *
     * @return price of the item
     * @author Rei
     */
    public abstract double getPrice();

    /**
     * Returns a string representation used in GUI lists.
     *
     * @return formatted string with name and price
     * @author Rei
     */
    @Override
    public String toString() {
        return name + " - " + getPrice() + " kr";
    }
}


