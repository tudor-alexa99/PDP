import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.locks.ReentrantLock;

public class Stock {
    private final List<StockProduct> productList;
    private int money;

    // every time a purchase is made, lock the money and quantity resources
    ReentrantLock moneyMutex = new ReentrantLock();

    public Stock() {
        this.money = 0;
        this.productList = new ArrayList<>();
    }

    public void addProduct(String productName, int price, int quantity) {
        StockProduct sp = new StockProduct(productName, price, quantity);
        this.productList.add(sp);
    }

    public BillItem sellProduct(String productName, int quantity) {
        for (StockProduct product : this.productList) {
            if (product.getName().equals(productName)) {
                // if the quantity is 0, stop the transaction
                // if it is not 0 but smaller than the required quantity, purchase only the available units and set it to 0
                // else, decrease the quantity with the purchased one and increase the money variable

                int availableQuantity = product.getPurchaseQuantity(quantity);

                if (availableQuantity == 0)
                    return null;


                int purchasePrice = availableQuantity * product.getPrice();
                BillItem bi = new BillItem(product.getProduct(), availableQuantity, purchasePrice);
                moneyMutex.lock();
                this.money += purchasePrice;
                moneyMutex.unlock();
                return bi;
            }

        }
        return null;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "productList=" + productList +
                ", money=" + money +
                '}';
    }

    public List<StockProduct> getProductList() {
        return productList;
    }

    public int getMoney() {
        return money;
    }
}
