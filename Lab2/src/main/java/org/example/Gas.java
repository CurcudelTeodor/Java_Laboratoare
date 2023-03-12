package org.example;

public class Gas extends Location {
    private double price;

    public Gas(String name, int x, int y) {
        super(name, "BENZINARIE", x, y);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
