import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int MAX_THREADS = 20;

    public static void main(String[] args) throws InterruptedException {

        PurchaseThread.initialise();

        List<PurchaseThread> threadsList = new ArrayList<PurchaseThread>();

        for (int i = 0; i < MAX_THREADS; i++) {
            PurchaseThread pt = new PurchaseThread("Thread " + i);
            threadsList.add(pt);
        }

        for (int i = 0; i < MAX_THREADS; i++) {
            threadsList.get(i).start();
        }

        PurchaseThread.verify();

        for (int i = 0; i < MAX_THREADS; i++) {
            threadsList.get(i).join();
            System.out.println(threadsList.get(i).printBill());
        }

        if (PurchaseThread.verify())
            System.out.println("Stock money: " + PurchaseThread.stock.getMoney());
            System.out.println("Billed total: " + PurchaseThread.getBilledTotal());
    }
}
