package Model;

    /**
     *Represents a pastry that can be ordered per unit.
     *
     * @author Rei
     */

    public class Pastry extends MenuItem {
        private double price;
        /**
         * Creates a pastry with name and fixed price.
         *
         * @param name name of the pastry
         * @param price price per unit
         * @author Rei
         */
        public Pastry(String name, double price) {
            super(name);
            this.price = price;
        }

        /**
         * Returns the price of the pastry.
         *
         * @return price of the pastry
         * @author Rei
         */
        @Override
        public double getPrice() {
            return price;
        }

        /**
         * Returns a string representation for GUI lists.
         *
         * @return string with name and price
         * @author Rei
         */
        @Override
        public String toString() {
            return getName() + " - " + price + " kr";
        }

    }
