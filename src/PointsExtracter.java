import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PointsExtracter {
    public static XYChart.Series<Double, Double> makeXYChartSeries(String name, ArrayList<Double> xAxis, ArrayList<Double> yAxis) {
        XYChart.Series<Double, Double> points = new XYChart.Series<>();
        points.setName(name);

        for (int i = 0; i < xAxis.size(); ++i) {
            var data = new XYChart.Data<>(xAxis.get(i), yAxis.get(i));
            points.getData().add(data);
        }

        return points;
    }
}