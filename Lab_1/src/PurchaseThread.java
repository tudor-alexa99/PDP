import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class PurchaseThread implements Runnable {
    private Thread t;
    private String tName;
    static Stock stock;
    static private ReentrantLock mutex = new ReentrantLock();
    private Bill currentBill;
    public static List<Bill> bills = new ArrayList<>();
    public static int PURCHASE_NUMBER = 10;
    public static int TIMEOUT = 3 * 1000; // timeout will be set to 5 seconds

    public PurchaseThread(String tName) {
        this.tName = tName;
        this.currentBill = new Bill();
    }

    public static void initialise() {
        stock = new Stock();
        bills = new ArrayList<>();

        stock.addProduct("Pineapple", 8, 1000);
        stock.addProduct("Juice", 10, 1200);
        stock.addProduct("Apples", 5, 2000);
        stock.addProduct("Pasta", 3, 800);
    }

    public void start() {
        System.out.println("Starting " + tName);
        if (t == null) {
            t = new Thread(this, tName);
            t.start();
        }
    }

    public void join() throws InterruptedException {
        if (t != null) {
            t.join();
        }
    }

    @Override
    public void run() {
        List<String> productNames = stock.getProductList().stream().map(StockProduct::getName).collect(Collectors.toList());


        // each thread makes a number of purchases
        for (int i = 0; i < PURCHASE_NUMBER; i++) {
            // buy a random product in a random quantity

            String product = productNames.get((int) (Math.random() * productNames.size()));
            int quantity = (int) (Math.random() * 100);

            if (i == PURCHASE_NUMBER / 2 ) {
                try {
                    // stop the thread at half the execution
                    Thread.sleep(TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            currentBill.addItems(stock.sellProduct(product, quantity));
        }
        mutex.lock();
        bills.add(currentBill);
        mutex.unlock();

    }

    public static boolean verify(){
        mutex.lock();
        int billedTotal = bills.stream().map(Bill::getTotalPrice).reduce(0, Integer::sum);
        boolean result = (billedTotal == stock.getMoney());
        mutex.unlock();
        return result;
    }

    public String printBill() {
        return tName + currentBill;
    }

    public static int getBilledTotal(){
        mutex.lock();
        int billedTotal = bills.stream().map(Bill::getTotalPrice).reduce(0, Integer::sum);
        mutex.unlock();
        return billedTotal;
    }
}
