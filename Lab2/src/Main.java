public class Main {
    public static int VECTOR_SIZE = 1000;
    public static int CAPACITY = 20;


    public static void main(String[] args) {
        int[] v1, v2;
        v1 = new int[VECTOR_SIZE];
        v2 = new int[VECTOR_SIZE];

        // populate the initial vectors
        for (int i = 0; i < VECTOR_SIZE; i++) {
            v1[i] = i;
            v2[VECTOR_SIZE - i - 1] = i;
        }

        SynchronizedQueue queue = new SynchronizedQueue(CAPACITY);

        // Create the run() method for the producer
        Runnable producer = () -> {
            for (int i = 0; i < VECTOR_SIZE; i ++) {
                queue.enqueue(v1[i] * v2[i]);
                System.out.println("Producer: " + v1[i] + " " + v2[i]);
            }
        };

        // Create the run() method for the consumer
        Runnable consumer = () -> {
            int step = 1;
            int sum = 0;
            // While we haven't been through all the values inside the vectors
            while (step != VECTOR_SIZE){
                int prod = queue.dequeue();
                sum += prod;
                step += 1;
                System.out.println("Consumer: " + prod);
            }
            System.out.println("Scalar product: " + sum);
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);


        producerThread.start();
        consumerThread.start();
        try {
            producerThread.join();
            consumerThread.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
