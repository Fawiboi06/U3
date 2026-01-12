package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer order containing menu items.
 *
 * @author DittNamn
 */
public class Order {

    private static int nextId = 1;

    private int id;
    private List<MenuItem> items;

    /**
     * Creates a new empty order.
     *
     * @author DittNamn
     */
    public Order() {
        this.id = nextId++;
        this.items = new ArrayList<>();
    }

    /**
     * Adds a menu item to the order.
     *
     * @param item the item to add
     * @author DittNamn
     */
    public void addItem(MenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Menu item cannot be null");
        }
        items.add(item);
    }

    /**
     * Returns all items in the order.
     *
     * @return list of menu items
     * @author DittNamn
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Calculates the total price of the order.
     *
     * @return total price
     * @author DittNamn
     */
    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice(); // POLYMORFISM
        }
        return total;
    }

    /**
     * Returns the order id.
     *
     * @return order id
     * @author DittNamn
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a string summary for order history list.
     *
     * @return order summary
     * @author DittNamn
     */
    @Override
    public String toString() {
        return "Order " + id + " - Total: " + getTotalPrice() + " kr";
    }
}
