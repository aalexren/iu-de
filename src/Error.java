public class Error extends NumericalSolution implements IError {
    public Error(double x0, double x, double y, double N) {
        super(x0, x, y, N);
    }

    @Override
    public void calcError(Solution sol) {
        ExactSolution exs = new ExactSolution(getX0(), getX(), getY(), getN());
        exs.solve();

//        yAxis.add(0, 0.0);
        for (int i = 0; i < getN() + 1; ++i) {
            yAxis.add(i, Math.abs(exs.yAxis.get(i) - sol.yAxis.get(i)));
        }
    }
}
