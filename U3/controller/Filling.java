package controller;

public enum Filling {
    VANILJKRAM("Vaniljkräm", 8),
    GRADDE("Grädde", 6),
    HALLONSYLT("Hallonsylt", 7),
    CHOKLADMOUSSE("Chokladmousse", 12),
    CHOKLADGANACHE("Chokladganache", 10),
    CITRONKRAM("Citronkräm", 9),
    MARANG("Maräng", 5);

    private final String displayName;
    private final double price;

    Filling(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }
}
