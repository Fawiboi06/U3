package controller;

public class Cake {
    private final String name;
    private final int sizeInPieces;
    private final Filling[] fillings;

    public Cake(String name, int sizeInPieces, Filling[] fillings) {
        this.name = name;
        this.sizeInPieces = sizeInPieces;
        this.fillings = fillings;
    }

    public String getName() {
        return name;
    }

    public int getSizeInPieces() {
        return sizeInPieces;
    }

    public Filling[] getFillings() {
        return fillings;
    }

    public double getPrice() {
        double basePrice = 40;
        double fillingsSum = 0;
        for (int i = 0; i < fillings.length; i++) {
            fillingsSum += fillings[i].getPrice();
        }

        double sizeMultiplier = sizeInPieces / 4.0;
        return (basePrice + fillingsSum) * sizeMultiplier;
    }

    public String toMenuString() {
        String fillingsText = buildFillingsText();
        int priceRounded = (int) Math.round(getPrice());
        return name + ", storlek: " + sizeInPieces + " bitar, fyllningar: " + fillingsText + ", pris: " + priceRounded + " kr";
    }

    private String buildFillingsText() {
        String text = "";
        for (int i = 0; i < fillings.length; i++) {
            text += fillings[i].getDisplayName();
            if (i < fillings.length - 1) {
                text += ", ";
            }
        }
        return text;
    }
}
