public class ImprovedEulerSolution extends NumericalSolution{

    public ImprovedEulerSolution(double x0, double x, double y, double N) {
        super(x0, x, y, N);
    }

    @Override
    public void solve() {
        double K1 = getStep() * Formula.initFunc(getX0(), getY());
        double K2 = getStep() * Formula.initFunc(getX0() + getStep(), getY() + K1);
        double y_prev = getY(); // y0
        double y_next = y_prev;
        yAxis.add(0, y_next);

        for (int i = 1; i < getN() + 1; ++i) {
            y_next = y_prev + 0.5 * (K1 + K2);
            yAxis.add(i, y_next);

            K1 = getStep() * Formula.initFunc(xAxis.get(i), y_next);
            K2 = getStep() * Formula.initFunc(xAxis.get(i) + getStep(), y_next + K1);
            y_prev = y_next;

//            System.out.println(y_next);
        }
    }
}
