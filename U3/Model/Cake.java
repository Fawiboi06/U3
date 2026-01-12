package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cake that can be ordered from the menu.
 * A cake has a size, base price and a list of fillings.
 * The price is calculated dynamically based on these attributes.
 *
 * @author Rei
 */


public class Cake extends MenuItem {
    private double basePrice;
    private int sizeInSlices;
    private List<Filling> fillings;

    /**
     * Creates a cake with given name, size, base price and fillings.
     *
     * @param name name of the cake
     * @param sizeInSlices number of slices
     * @param basePrice base price of the cake
     * @param fillings list of fillings (minimum 2)
     * @author Rei
     */

    public Cake(String name, int sizeInSlices,double basePrice, List<Filling> fillings) {
        super(name);

        if (fillings == null || fillings.size() < 2) {
            throw new IllegalArgumentException("A cake must have at least two fillings.");
        }

        this.sizeInSlices = sizeInSlices;
        this.basePrice = basePrice;
        this.fillings = new ArrayList<>(fillings);
    }

    /**
     * Returns number of slices.
     *
     * @return number of slices
     * @author Rei
     */
    public int getSizeInSlices() {
        return sizeInSlices;
    }

    /**
     * Calculates and returns the price of the cake.
     * Price is based on base price, fillings and size.
     *
     * @return total price of the cake
     * @author Rei
     */
    @Override
    public double getPrice() {
        double fillingCost = 0;

        for (Filling f : fillings) {
            fillingCost += f.getPrice();
        }

        return (basePrice + fillingCost) * sizeInSlices;
    }

    /**
     * Returns a string representation of the cake for GUI lists.
     *
     * @return formatted cake description
     * @author Rei
     */
    @Override
    public String toString() {
        return getName() + ", " + sizeInSlices + " slices, fillings: " + fillings + ", price: " + getPrice() + " kr";
    }
}






