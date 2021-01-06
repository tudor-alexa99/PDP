public class Main {
    private static int THREADS_COUNT = 8;

    public static void main(String[] args) throws InterruptedException {

        Polynomial p1 = new Polynomial(10000);
        Polynomial p2 = new Polynomial(10000);

        p1.setRandomCoefficients();
        p2.setRandomCoefficients();



        long startTime1 = System.currentTimeMillis();
        System.out.println(Multiplier.simpleSequentialProduct(p1, p2));
        long endTime1 = System.currentTimeMillis();

        long startTime2 = System.currentTimeMillis();
        System.out.println(Multiplier.simpleParallelProduct(p1,p2, THREADS_COUNT));
        long endTime2 = System.currentTimeMillis();

        System.out.println("\n\nPerformance for polynomials with " + p1.getDegree() + " coefficients: ");
        System.out.println("(1) O(n2) algorithm - sequential form: " + (endTime1 - startTime1) + " ms");
        System.out.println("(2) O(n2) algorithm - parallelize form: " + (endTime2 - startTime2) + " ms");
    }
}
