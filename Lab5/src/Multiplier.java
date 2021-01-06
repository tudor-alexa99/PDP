import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Multiplier {

    public static Polynomial simpleSequentialProduct(Polynomial p1, Polynomial p2) {
        /*
        Multiply each coefficient from p1 with each coefficient from p2
        and save the result in a new Polynomial, on the position i + j
         */

        Polynomial result = new Polynomial(p1.getDegree() + p2.getDegree());

        for (int i = 0; i <= p1.getDegree(); i++)
            for (int j = 0; j <= p2.getDegree(); j++) {
                int product = p1.getCoefficient(i) * p2.getCoefficient(j);
                result.increaseCoefficient(i + j, product);
            }
        return result;
    }

    public static Polynomial simpleParallelProduct(Polynomial p1, Polynomial p2, int threadsNumber) throws InterruptedException {
        Polynomial result = new Polynomial(p1.getDegree() + p2.getDegree());

        // if the number of threads is higher than needed, limit it to the length of the result
        if (threadsNumber >= result.length())
            threadsNumber = result.length();

        // count how many operations each thread will have to perform
        int operationsCount = result.length() / threadsNumber;

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsNumber);

        // each thread will start performing the multiplication from a threadStart to a threadEnd position inside the
        // polynomial

        for (int threadPosition = 0; threadPosition < result.length(); threadPosition += operationsCount) {
            int threadStart = threadPosition;
            int threadEnd = threadStart + operationsCount;

            threadPoolExecutor.execute(() -> {
                for (int pos = threadStart; pos < threadEnd; pos++) {
                    // find all the combination of coefficients that are used to obtain the value in the result
                    if (pos > result.getDegree())
                        return;
                    for (int i = 0; i <= pos; i++)
                        if (i <= p1.getDegree() && (pos - i) <= p2.getDegree()) {
                            int product = p1.getCoefficient(i) * p2.getCoefficient(pos - i);
                            result.increaseCoefficient(pos, product);
                        }

                }
            });
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(50, TimeUnit.SECONDS);
        return result;
    }

//    public static Polynomial karatsubaSequentialProduct(Polynomial p1, Polynomial p2) {
//        List<Integer> coefficients = karatsubaSequential(p1.getCoefficients(), p2.getCoefficients());
//        coefficients.remove(coefficients.size() - 1);
//        return new Polynomial(coefficients);
//    }

//    private static List<Integer> karatsubaSequential(List<Integer> L, List<Integer> R) {
//        List<Integer> coefficients = Polynomial.emptyPolynomial(L.size() * 2);
//        if (L.size() == 1) {
//            coefficients.set(0, L.get(0) * R.get(0));
//            return coefficients;
//        }
//        int size = L.size() / 2;
//        List<Integer> a = Polynomial.emptyPolynomial(size);
//        List<Integer> b = Polynomial.emptyPolynomial(size);
//        List<Integer> c = Polynomial.emptyPolynomial(size);
//        List<Integer> d = Polynomial.emptyPolynomial(size);
//        List<Integer> a_b = Polynomial.emptyPolynomial(size);
//        List<Integer> c_d = Polynomial.emptyPolynomial(size);
//
//
//        for (int i = 0; i < size; i++) {
//            b.set(i, L.get(i));
//            d.set(i, R.get(i));
//            a.set(i, L.get(size + i));
//            c.set(i, R.get(size + i));
//            a_b.set(i, a.get(i) + b.get(i));
//            c_d.set(i, c.get(i) + d.get(i));
//        }
//
//        //a*c
//        List<Integer> prod1 = karatsubaSequential(a, c);
//        // b* d
//        List<Integer> prod2 = karatsubaSequential(b, d);
//        // (a+b) * (c+d)
//        List<Integer> prod12 = karatsubaSequential(a_b, c_d);
//
//        List<Integer> prod = Polynomial.emptyPolynomial(L.size());
//        ///(a+b) * (c+d) - a*c -b*d
//        for (int i = 0; i < L.size(); i++)
//            prod.set(i, prod12.get(i) - prod1.get(i) - prod2.get(i));
//        coefficients = Polynomial.emptyPolynomial(L.size() * 2);
//        for (int i = 0; i < L.size(); i++) {
//            coefficients.set(i, coefficients.get(i) + prod2.get(i));
//            coefficients.set(i + L.size(), coefficients.get(i + L.size()) + prod1.get(i));
//            coefficients.set(i + L.size() / 2, coefficients.get(i + L.size() / 2) + prod.get(i));
//        }
//        return coefficients;
//    }
}
