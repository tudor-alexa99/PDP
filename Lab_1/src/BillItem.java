public class BillItem {
    private Product product;
    private int purchasedQuantity;
    private int totalPrice;

    public BillItem(Product product, int purchasedQuantity, int totalPrice) {
        this.product = product;
        this.purchasedQuantity = purchasedQuantity;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "BillItem{" +
                "product=" + product +
                ", purchasedQuantity=" + purchasedQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
