package models;

public class LineItem {
    private Product product;
    private int quantity;
    private double subtotal;

    public LineItem() {
        this.product = null;
        this.quantity = 0;
        this.subtotal = 0.0;
    }

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = calcSubtotal();
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return subtotal; }

    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double calcSubtotal() {
        if (product != null) {
            return product.calcPrice(this.quantity) * quantity;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        if (product != null) {
            return "LineItem{product=" + product.getProductName() + ", quantity=" + quantity + ", subtotal=" + subtotal + "}";
        } else {
            return "LineItem{product=None, quantity=" + quantity + ", subtotal=" + subtotal + "}";
        }
    }
}