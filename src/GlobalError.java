import java.util.Collections;

interface IGlobalError {
    public void calcError(Error err, EulerSolution sol);
    public void calcError(Error err, ImprovedEulerSolution sol);
    public void calcError(Error err, RungeKuttaSolution sol);
}

public class GlobalError extends NumericalSolution implements IGlobalError {
    private double N_MAX;

    public GlobalError(double x0, double x, double y, double N, double N_MAX) {
        super(x0, x, y, N);
        this.N_MAX = N_MAX;
        xAxis.clear();
    }


    // TODO overloading for 3 methods
    @Override
    public void calcError(Error err, EulerSolution sol) {

        for (int i = (int) getN(); i < N_MAX; ++i) {
            Error nerr = new Error(getX0(), getX(), getY(), i);
            EulerSolution nsol = new EulerSolution(getX0(), getX(), getY(), i);
            nsol.solve();
            nerr.calcError(nsol);
            yAxis.add(Collections.max(nerr.yAxis));
            xAxis.add(Double.valueOf(i));
        }
    }

    @Override
    public void calcError(Error err, ImprovedEulerSolution sol) {

        for (int i = (int) getN(); i < N_MAX; ++i) {
            Error nerr = new Error(getX0(), getX(), getY(), i);
            ImprovedEulerSolution nsol = new ImprovedEulerSolution(getX0(), getX(), getY(), i);
            nsol.solve();
            nerr.calcError(nsol);
            yAxis.add(Collections.max(nerr.yAxis));
            xAxis.add(Double.valueOf(i));
        }
    }

    @Override
    public void calcError(Error err, RungeKuttaSolution sol) {

        for (int i = (int) getN(); i < N_MAX; ++i) {
            Error nerr = new Error(getX0(), getX(), getY(), i);
            RungeKuttaSolution nsol = new RungeKuttaSolution(getX0(), getX(), getY(), i);
            nsol.solve();
            nerr.calcError(nsol);
            yAxis.add(Collections.max(nerr.yAxis));
            xAxis.add(Double.valueOf(i));
        }
    }
}
