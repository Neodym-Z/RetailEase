package models;

public class Product {
    protected int productID;
    protected String name;
    protected double price;
    protected int stockQuantity;

    public Product() {
        this.productID = 0;
        this.name = null;
        this.price = 0.0;
        this.stockQuantity = 0;
    }

    public Product(int productID, String name, double price, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductID() { return productID; }
    public String getProductName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public void setProductID(int productID) { this.productID = productID; }
    public void setProductName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", name='" + name + '\'' + ", price=" + price + ", stockQuantity=" + stockQuantity + '}';
    }

    public double calcPrice(int quantity) {
        return this.price * quantity;
    }

    public void updateStock(int quantity) {
        if (this.stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
        } else {
            System.out.println("Warning: Insufficient stock for " + this.name);
        }
    }
}