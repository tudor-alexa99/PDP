import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Algorithm {
    private boolean[][] matrix;
    private AtomicBoolean foundSoulution;
    ReentrantLock mutex;
    private ArrayList<Integer> colors = new ArrayList<>();
    private int colorsCount;

    private void generateRandom(int nodesCount) {
        return;
    }

    Boolean validColor(int node, int color, ArrayList<Integer> currentColors) {
        for (int i = 0; i < currentColors.size(); i++) {
            if (matrix[node][i] == true && color == currentColors.get(i))
                return false;
        }
        return true;
    }

    void backtracking(ArrayList<Integer> temp, int T, int k, int n) throws InterruptedException {

        if (foundSoulution.get()) return;

        if (temp.size() == n) {
            mutex.lock();
            if (!foundSoulution.get()) {
                for (int i = 0; i < n; i++)
                    colors.add(temp.get(i));
                foundSoulution.set(true);
            }
            mutex.unlock();
            return;
        }

        if (T == 1) {
            for (int color = 1; color <= k; color++) {
                if (validColor(temp.size(), color, temp)) {
                    temp.add(color);
                    backtracking(temp, T, k, n);
                    temp.remove(temp.size() - 1);
                }
            }
        } else {
            ArrayList<Integer> copy = temp;

            Thread thread;
            thread = new Thread(() -> {
                for (int color = 1; color <= k; color += 2) {
                    copy.add(color);
                    try {
                        backtracking(copy, T / 2, k, n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    copy.remove(copy.size() - 1);
                }
            });

            for (int color = 2; color <= k; color += 2) {
                if (validColor(temp.size(), color, temp)) {
                    temp.add(color);
                    backtracking(temp, T - T / 2, k, n);
                    temp.remove(temp.size() - 1);
                }
            }
            thread.join();
        }
    }

}

