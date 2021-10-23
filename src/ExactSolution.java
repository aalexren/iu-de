import javafx.scene.chart.XYChart;

public class ExactSolution extends Solution {

    public ExactSolution(double x0, double x, double y, double N) {
        super(x0, x, y, N);
    }

    @Override
    public void solve() {
        for (int i = 0; i < getN() + 1; ++i) {
            var ix = xAxis.get(i);
            var ry = 3 * ix * ix - ix; // resulting y
            yAxis.add(i, ry);
//            System.out.println(ix);
//            System.out.println(ry);
        }
    }
}
