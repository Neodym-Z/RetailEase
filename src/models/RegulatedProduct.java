package models;

public class RegulatedProduct extends Product {
    private int minAge;

    public RegulatedProduct() {
        super();
        this.minAge = 0;
    }

    public RegulatedProduct(int productID, String name, double price, int stockQuantity, int minAge) {
        super(productID, name, price, stockQuantity);
        this.minAge = minAge;
    }

    public int getMinAge() { return minAge; }

    public void setMinAge(int minAge) { this.minAge = minAge; }

    @Override
    public double calcPrice(int quantity) {
        return super.calcPrice(quantity);
    }

    public boolean checkEligibility(Customer customer) {
        return customer.getAge() >= this.minAge;
    }

    @Override
    public String toString() {
        return "RegulatedProduct{" + "productID=" + productID + ", name='" + name + '\'' + ", minAge=" + minAge + '}';
    }
}