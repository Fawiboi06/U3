package Model;

/**
 * Enum representing different cake fillings with individual prices.
 *
 * @author Rei
 */
public enum Filling {

    CHOCOLATE(15),
    VANILLA(10),
    RASPBERRY(12),
    BLUEBERRY(13),
    OREO(18);

    private final double price;

    /**
     * Creates a filling with a given price.
     *
     * @param price price of the filling
     * @author Rei
     */
    Filling(double price) {
        this.price = price;
    }

    /**
     * Returns the price of this filling.
     *
     * @return price of filling
     * @author Rei
     */
    public double getPrice() {
        return price;
    }
}
