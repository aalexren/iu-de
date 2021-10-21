import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainView {
    protected Scene scene;
    protected LineChart<Double, Double> lineChart;
    protected Button btnCalculate;

    protected TextField tbxX0;
    protected TextField tbxX;
    protected TextField tbxY;
    protected TextField tbxN;

    public MainView() {
        buildLineChart();
        buildInput();

        Group group = new Group(lineChart);
        group.getChildren().add(tbxX0);
        group.getChildren().add(tbxX);
        group.getChildren().add(tbxY);
        group.getChildren().add(tbxN);
        group.getChildren().add(btnCalculate);
        scene = new Scene(group);
    }

    public Scene getScene() {
        return scene;
    }

    protected void buildLineChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x Axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y Axis");

        lineChart = new LineChart(xAxis, yAxis);

//        XYChart.Series<Double, Double> dataSeries1 = new XYChart.Series<>();
//        dataSeries1.setName("2014");
//
//        dataSeries1.getData().add(new XYChart.Data( 1, 567));
//        dataSeries1.getData().add(new XYChart.Data( 5, 612));
//        dataSeries1.getData().add(new XYChart.Data(10, 800));
//        dataSeries1.getData().add(new XYChart.Data(20, 780));
//        dataSeries1.getData().add(new XYChart.Data(40, 810));
//        dataSeries1.getData().add(new XYChart.Data(80, 850));

        ExactSolution exactSolution = new ExactSolution("Exact Solution");
        lineChart.getData().add(exactSolution.getPoints(1, 10, 200));
        lineChart.getData().add(exactSolution.getPoints(1, 10, 20));

        lineChart.setLayoutX(10);
        lineChart.setLayoutX(10);

        lineChart.setPrefWidth(600);
        lineChart.setMinWidth(600);
        lineChart.setMaxWidth(600);

        lineChart.setPrefHeight(600);
        lineChart.setMinHeight(600);
        lineChart.setMaxHeight(600);

        lineChart.setCreateSymbols(false); // убрать обводку точек на графике
    }

    protected void buildInput() {
        btnCalculate = new Button("Calculate");
        btnCalculate.setLayoutX(700);
        btnCalculate.setLayoutY(200);

        tbxX0 = new TextField();
        tbxX = new TextField();
        tbxY = new TextField();
        tbxN = new TextField();

        tbxX0.setLayoutX(700);
        tbxX0.setLayoutY(100);
        tbxX0.setMaxSize(80, 20);
        tbxX0.setMinSize(40, 20);

        tbxX.setLayoutX(700);
        tbxX.setLayoutY(140);
//        tbxX0.setMaxSize(40, 20);

        tbxY.setLayoutX(700);
        tbxY.setLayoutY(180);
//        tbxX0.setMaxSize(40, 20);

        tbxN.setLayoutX(700);
        tbxN.setLayoutY(220);
//        tbxX0.setMaxSize(40, 20);
    }
}