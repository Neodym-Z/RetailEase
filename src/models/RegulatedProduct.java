package models;

public class RegulatedProduct extends Product {
    private int minAge;

    public RegulatedProduct(int productID, String name, double price, int stockQuantity, int minAge) {
        super(productID, name, price, stockQuantity);
        this.minAge = minAge;
    }

    // Overriding the base method
    @Override
    public double calcPrice() {
        return super.calcPrice(); // Just calls the base Product calcPrice()
    }

    public boolean checkEligibility(int customerAge) {
        return customerAge >= this.minAge;
    }
}
