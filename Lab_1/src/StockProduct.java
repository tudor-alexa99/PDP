import java.util.concurrent.locks.ReentrantLock;

public class StockProduct {
    private final Product product;
    int quantity;

    ReentrantLock mutex = new ReentrantLock();

    public StockProduct(String productName, int productPrice, int quantity) {
        this.product = new Product(productName, productPrice);
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public String getName() {
        return this.product.getName();
    }

    public int getPrice() {
        return this.product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "StockProduct{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    public int getPurchaseQuantity(int quantity) {
        mutex.lock();
        int q = 0;
        if (this.quantity < quantity){
            q = this.quantity;
            this.quantity = 0;
            mutex.unlock();
            return q;
        }
        else {
            q = this.quantity - quantity;
            mutex.unlock();
            return q;
        }
    }
}
