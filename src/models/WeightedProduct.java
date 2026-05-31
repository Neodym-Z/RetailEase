package models;

public class WeightedProduct extends Product {
    private double weight;
    private double pricePerKg;

    public WeightedProduct(int productID, String name, int stockQuantity, double weight, double pricePerKG) {
        super(productID, name, 0.0, stockQuantity); // Base price is 0.0 because it's calculated by weight
        this.weight = weight;
        this.pricePerKG = pricePerKG;
    }

    @Override
    public double calcPrice() {
        return this.weight * this.pricePerKG;
    }

    // TODO: Getters and setters for weight and pricePerKG...
}


