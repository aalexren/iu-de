import java.text.Normalizer;

public class EulerSolution extends NumericalSolution {

    public EulerSolution(double x0, double x, double y, double N) {
        super(x0, x, y, N);
    }


    /*
        y_{i+1} = y_i + h * f(x_i, y_i);
     */
    @Override
    public void solve() {
        yAxis.add(0, getY()); //y0
        for (int i = 1; i < getN() + 1; ++i) {
            var y_next = yAxis.get(i - 1) + getStep() * Formula.initFunc(xAxis.get(i-1), yAxis.get(i-1));
            yAxis.add(i, y_next);
//            System.out.println(y_next);
        }
    }
}