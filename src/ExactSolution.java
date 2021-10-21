import javafx.scene.chart.XYChart;


/*
 *
 */
public class ExactSolution {
    private XYChart.Series<Double, Double> points;

    public ExactSolution(String name) {
        points = new XYChart.Series<>();
        points.setName(name);
    }

    public void buildPoints(double x, double x0, int N) {
        points = new XYChart.Series<>();
        double step = (x0 - x) / N;
        double ix = x; // initial x

        for (int i = 0; i < N + 1; ++i) {
            var ry = 3 * ix * ix - ix; // resulting y
            points.getData().add(new XYChart.Data<>(ix, ry));
            ix += step;
        }
    }

    // N - steps
    public XYChart.Series getPoints(double x, double x0, int N) {
        buildPoints(x, x0, N);
        return points;
    }
}
