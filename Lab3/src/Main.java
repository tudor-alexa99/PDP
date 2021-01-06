import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Initialise the matrix
        int totalThreads = 8;

        int matrix_size = 1000;
        int[][] A = new int[matrix_size][matrix_size];
        int[][] B = new int[matrix_size][matrix_size];

        for (int i = 0; i < matrix_size; i++) {
            for (int j = 0; j < matrix_size; j++) {
                // generate random values between 1 and 10 for the matrix
                A[i][j] = (int) (1 + Math.random() * 10);
                B[i][j] = (int) (1 + Math.random() * 10);
            }
        }

        Program program = new Program(matrix_size, totalThreads, A, B);

        program.simpleThreadsTask1();
        program.simpleThreadsTask2();
        program.simpleThreadsTask3();

        program.printResult("\n\n");

        program.threadPoolTask1();
        program.threadPoolTask2();
        program.threadPoolTask3();

        program.printResult("\n\n");
    }
}