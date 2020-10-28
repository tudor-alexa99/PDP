public class GuruThread1 implements Runnable {
    public static void main(String[] args) {
        Thread thread1 = new Thread("First");
        Thread thread2 = new Thread("Second");

        thread1.start();
        thread2.start();

        System.out.println("The thread names are the following:");
        System.out.println(thread1.getName());
        System.out.println(thread2.getName());
    }

    @Override
    public void run() {

    }
}
