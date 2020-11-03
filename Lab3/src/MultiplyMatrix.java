public class MultiplyMatrix {
    private int size;
    private int totalThreads;
    private int[][] A;
    private int[][] B;
    private int[][] result;
    private int currentThread;

    public MultiplyMatrix(int size, int totalThreads, int[][] a, int[][] b) {
        this.size = size;
        this.totalThreads = totalThreads;
        A = a;
        B = b;
        this.currentThread = 0;
        this.result = new int[size][size];
    }

    public void multiplyTask(int row_A, int col_B) {
//        multiplies the elements from the selected row_A with the ones from column_B
        for (int i = 0; i < this.size; i++)
            this.result[row_A][col_B] += A[row_A][i] * B[i][col_B];
    }

    public Runnable task1 = () -> {
//        The number of the current thread (the one executing the task) will determine the position
//        in the result matrix where the multiplication process should end

//        Task 1 adds results to the matrix in row order
        int position = this.currentThread;
        this.currentThread++;

        for (int row_A = position * size / totalThreads; row_A < (position + 1) * size / totalThreads; row_A++) {
            for (int col_B = position * size / totalThreads; col_B < (position + 1) * size / totalThreads; col_B++)
                multiplyTask(row_A, col_B);
        }
    };

    public Runnable task2 = () -> {
//        Add the products in the result matrix by columns

        int position = this.currentThread;
        this.currentThread++;

        for (int row_A = 0; row_A < size; row_A++)
            for (int col_B = position * size / totalThreads; col_B < (position + 1) * size / totalThreads; col_B++) {
                multiplyTask(row_A, col_B);
            }
    };

    public Runnable task3 = () -> {
//        Add the products to the matrix in a step-over way, each thread on its corresponding index

        int position = this.currentThread;
        this.currentThread++;

        for (int row_A = position; row_A < size; row_A += totalThreads)
            for (int col_B = 0; col_B < size; col_B++)
                multiplyTask(row_A, col_B);
    };

    public int[][] getResult() {
        return this.result;
    }
}
