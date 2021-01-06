import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Program {
    private int size;
    private int totalThreads;
    private int[][] A;
    private int[][] B;

    public Program(int size, int totalThreads, int[][] a, int[][] b) {
        this.size = size;
        this.totalThreads = totalThreads;
        A = a;
        B = b;
    }

    public void simpleThreadsTask1() throws InterruptedException, IOException {
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix(size, totalThreads, A, B);
        long startingTime = System.currentTimeMillis();

        List<Thread> threadList = new ArrayList<>();

//        Create the threads
        for (int i = 0; i < totalThreads; i++) {
            threadList.add(new Thread(multiplyMatrix.task1));
        }

//        Start the threads
        for (Thread thread : threadList)
            thread.start();

//        Join the threads
        for (Thread thread : threadList)
            thread.join();

        long endingTime = System.currentTimeMillis();

        printResult("Task 1 simple threads execution time: " + (endingTime - startingTime) +
                " Matrix size: " + size +
                " Number of threads: " + totalThreads +
                "\n");
//        printTest(multiplyMatrix);

    }

    public void simpleThreadsTask2() throws InterruptedException, IOException {
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix(size, totalThreads, A, B);
        long startingTime = System.currentTimeMillis();

        List<Thread> threadList = new ArrayList<>();

//        Create the threads
        for (int i = 0; i < totalThreads; i++) {
            threadList.add(new Thread(multiplyMatrix.task2));
        }

//        Start the threads
        for (Thread thread : threadList)
            thread.start();

//        Join the threads
        for (Thread thread : threadList)
            thread.join();

        long endingTime = System.currentTimeMillis();

        printResult("Task 2 simple threads execution time: " + (endingTime - startingTime) +
                " Matrix size: " + size +
                " Number of threads: " + totalThreads +
                "\n");
//        printTest(multiplyMatrix);

    }

    public void simpleThreadsTask3() throws InterruptedException, IOException {
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix(size, totalThreads, A, B);
        long startingTime = System.currentTimeMillis();

        List<Thread> threadList = new ArrayList<>();

//        Create the threads
        for (int i = 0; i < totalThreads; i++) {
            threadList.add(new Thread(multiplyMatrix.task3));
        }

//        Start the threads
        for (Thread thread : threadList)
            thread.start();

//        Join the threads
        for (Thread thread : threadList)
            thread.join();

        long endingTime = System.currentTimeMillis();

        printResult("Task 3 simple threads execution time: " + (endingTime - startingTime) +
                " Matrix size: " + size +
                " Number of threads: " + totalThreads +
                "\n");
//        printTest(multiplyMatrix);

    }


    public void threadPoolTask1() throws IOException, InterruptedException {
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix(size, totalThreads, A, B);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        long startingTime = System.currentTimeMillis();
        for(int i = 0; i < totalThreads; i ++)
            executorService.submit(multiplyMatrix.task1);


        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long endingTime = System.currentTimeMillis();

        printResult("Task 1 Thread Pool execution time: " + (endingTime - startingTime) +
                " Matrix size: " + size +
                " Number of threads: " + totalThreads +
                "\n");
//        printTest(multiplyMatrix);
    }

    public void threadPoolTask2() throws IOException, InterruptedException {
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix(size, totalThreads, A, B);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        long startingTime = System.currentTimeMillis();
        for(int i = 0; i < totalThreads; i ++)
            executorService.submit(multiplyMatrix.task2);


        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long endingTime = System.currentTimeMillis();

        printResult("Task 2 Thread Pool execution time: " + (endingTime - startingTime) +
                " Matrix size: " + size +
                " Number of threads: " + totalThreads +
                "\n");
//        printTest(multiplyMatrix);
    }

    public void threadPoolTask3() throws IOException, InterruptedException {
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix(size, totalThreads, A, B);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        long startingTime = System.currentTimeMillis();
        for(int i = 0; i < totalThreads; i ++)
            executorService.submit(multiplyMatrix.task3);


        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long endingTime = System.currentTimeMillis();

        printResult("Task 3 Thread Pool execution time: " + (endingTime - startingTime) +
                " Matrix size: " + size +
                " Number of threads: " + totalThreads +
                "\n");

//        printTest(multiplyMatrix);
    }


    public static void printResult(String output) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("output.txt", true));

        writer.write(output);
        writer.close();
    }


    public void printTest(MultiplyMatrix multiplyMatrix){
        multiplyMatrix.printMatrix(A);
        multiplyMatrix.printMatrix(B);
        multiplyMatrix.printMatrix(multiplyMatrix.getResult());
    }
//    public String getResult(){
//        String s = "";
//        for (int i = 0; i < size; i ++ )
//            for (int j = 0; j < size; j ++){
//                s += res
//            }
//    }
}
