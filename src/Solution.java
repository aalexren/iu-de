import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Solution {
    protected ArrayList<Double> xAxis;
    protected ArrayList<Double> yAxis;

    private double x;
    private double x0;
    private double y;
    private double N;

    private double h; // step

    public Solution(double x0, double x, double y, double N) {
        xAxis = new ArrayList<>();
        yAxis = new ArrayList<>();

        this.x = x;
        this.x0 = x0;
        this.y = y;
        this.N = N;

        buildPoints();
    }

    private void buildPoints() {
        h = (x - x0) / N;
        var cur_x = x0;

        for (int i = 0; i < N + 1; ++i) {
            xAxis.add(cur_x);
            cur_x += h;
//            yAxis.add(0.0);
        }
    }

    public void solve() {

    }

    public ArrayList<Double> getxAxis() {
        return xAxis;
    }

    public ArrayList<Double> getyAxis() {
        return yAxis;
    }

    public double getStep() {
        return h;
    }

    public double getX() {
        return x;
    }

    public double getX0() {
        return x0;
    }

    public double getY() {
        return y;
    }

    public double getN() {
        return N;
    }
}
