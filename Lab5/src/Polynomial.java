import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Integer> coefficients;
    private int degree;

    public Polynomial(List<Integer> coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.size() - 1;
    }

    public void increaseCoefficient(int pos, int value) {
        int oldValue = this.coefficients.get(pos);
        this.coefficients.set(pos, oldValue + value);
    }

    public int getCoefficient(int pos) {
        return this.coefficients.get(pos);
    }

    public Polynomial(int degree) {
        this.coefficients = new ArrayList<>();
        this.degree = degree;
        // Initialise the list of coefficients with 0
        for (int i = 0; i <= this.degree; i++) {
            coefficients.add(0);
        }
    }

    public int length() {
        return this.coefficients.size();
    }

    public List<Integer> getCoefficients() {
        return coefficients;
    }

    public int getDegree() {
        return degree;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(this.coefficients.get(0));
        for (int i = 1; i < this.coefficients.size(); i++) {
            if (this.coefficients.get(i) != 0) {
                out
                        .append(" + ")
                        .append(this.coefficients.get(i))
                        .append("X^")
                        .append(i);
            }
        }
        return out.toString();
    }

    public static List<Integer> emptyPolynomial(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(0);
        return list;
    }

    public void setRandomCoefficients(){
        for (int i = 0; i < this.coefficients.size(); i ++){
            int value = (int)(Math.random() *(1 + 10 )) + 1;
            this.coefficients.set(i, value);
        }
    }
}
