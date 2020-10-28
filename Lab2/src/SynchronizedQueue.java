import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedQueue {
    /* Create a FIFO blocking queue */
    Queue<Integer> queue;
    int capacity;
    ReentrantLock mutex;
    Condition condition;

    public SynchronizedQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.mutex = new ReentrantLock();
        condition = mutex.newCondition();
        this.capacity = capacity;
    }

    public void enqueue(int product){
        mutex.lock();
        try {
            // if  the queue is full, wait for the consumer to take some values out
            while (queue.size() == this.capacity){
                condition.await();
            }
            queue.add(product);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            mutex.unlock();
        }
    }

    public int dequeue(){
        mutex.lock();
        try {
            // if  the queue is empty and the producer has not finished yet, wait for it to add more values
            while (queue.size() == 0){
                condition.await();
            }
            int element = this.queue.remove();
            condition.signalAll();
            return element;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
        finally {
            mutex.unlock();
        }
    }
}
