public class RungeKuttaSolution extends NumericalSolution {
    public RungeKuttaSolution(double x0, double x, double y, double N) {
        super(x0, x, y, N);
    }

    @Override
    public void solve() {
        yAxis.add(0, getY());
        double K1;
        double K2;
        double K3;
        double K4;

        for (int i = 1; i < getN() + 1; ++i) {
            var x_i = xAxis.get(i-1);
            var y_i = yAxis.get(i-1);
            K1 = getStep() * Formula.initFunc(x_i, y_i);
            K2 = getStep() * Formula.initFunc(x_i + getStep() / 2, y_i + 0.5 * K1);
            K3 = getStep() * Formula.initFunc(x_i + getStep() / 2, y_i + 0.5 * K2);
            K4 = getStep() * Formula.initFunc(x_i + getStep(), y_i + K3);

            var y_next = y_i + (K1 + 2 * K2 + 2 * K3 + K4) / 6;
            yAxis.add(i, y_next);
            System.out.println(y_next);
        }
    }
}
