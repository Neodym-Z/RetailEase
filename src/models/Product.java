package models;

public class Product {
    // 1. ATTRIBUTES
    protected int productID;
    protected String name;
    protected double price;
    protected int stockQuantity;

    // 2. DEFAULT CONSTRUCTOR (No-arg constructor)
    public Product() {
        this.productID = 0;
        this.name = null;
        this.price = 0.0;
        this.stockQuantity = 0;
    }

    // 3. NORMAL CONSTRUCTOR (Parameterized constructor)
    public Product(int productID, String name, double price, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // 4. GETTERS AND SETTERS

    // Product ID
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // 5. TOSTRING METHOD
    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }

    public double calcPrice()
        // TODO: Implement calculation or discount checking logic later
        return this.price;
    }

    public void updateStock(int quantity) { [cite: 88]
        // TODO: Implement stock update logic later
        this.stockQuantity += quantity;
    }
}