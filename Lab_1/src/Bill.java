import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.locks.ReentrantLock;

public class Bill {
    private int totalPrice;
    private List<BillItem> itemsList;
    private ReentrantLock mutex;

    public Bill() {
        this.totalPrice = 0;
        this.itemsList = new ArrayList<>();
        this.mutex = new ReentrantLock();
    }

    public void addItems(BillItem items) {
        if (items != null) {
            mutex.lock();
            this.itemsList.add(items);
            this.totalPrice += items.getTotalPrice();
            mutex.unlock();
        }
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<BillItem> getItemsList() {
        return itemsList;
    }

    @Override
    public String toString() {
        // a mutex was needed in order to prevent adding a value while trying to return the current array
        mutex.lock();
        String result = "Bill{" +
                "totalPrice=" + totalPrice +
                ", itemsList=" + itemsList +
                '}';
        mutex.unlock();
        return result;
    }
}
