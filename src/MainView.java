import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

public class MainView {
    protected Scene scene;
    protected LineChart<Double, Double> lineChart;
    protected Button btnCalculate;

    /* INPUT FIELDS */
    protected TextField tbxX0;
    protected Label lblX0;

    protected TextField tbxX;
    protected Label lblX;

    protected TextField tbxY;
    protected Label lblY;

    protected TextField tbxN;
    protected Label lblN;

    protected RadioButton rbMain;
    protected RadioButton rbLTE;
    protected RadioButton rbGTE;
    protected ToggleGroup radioGroup;

    ExactSolution exactSolution;
    EulerSolution eulerSolution;
    ImprovedEulerSolution improvedEulerSolution;
    RungeKuttaSolution rungeKuttaSolution;

    public MainView() {
        buildPagination();
        buildInput();
        buildLineChart();

        Group group = new Group(lineChart);
        group.getChildren().add(tbxX0);
        group.getChildren().add(tbxX);
        group.getChildren().add(tbxY);
        group.getChildren().add(tbxN);
        group.getChildren().add(lblX0);
        group.getChildren().add(lblX);
        group.getChildren().add(lblY);
        group.getChildren().add(lblN);
        group.getChildren().add(btnCalculate);
        group.getChildren().add(rbGTE);
        group.getChildren().add(rbLTE);
        group.getChildren().add(rbMain);
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

        lineChart.setLayoutX(10);
        lineChart.setLayoutX(10);

        lineChart.setPrefWidth(850);
        lineChart.setMinWidth(850);
        lineChart.setMaxWidth(850);

        lineChart.setPrefHeight(575);
        lineChart.setMinHeight(575);
        lineChart.setMaxHeight(575);

        lineChart.setCreateSymbols(false); // убрать обводку точек на графике

        btnCalculate.fire(); // build chart
    }

//    protected void buildLTELineChart() {
//        NumberAxis xAxis = new NumberAxis();
//        xAxis.setLabel("x Axis");
//
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("y Axis");
//
//        lineChartLTE = new LineChart(xAxis, yAxis);
//
//        lineChartLTE.setLayoutX(10);
//        lineChartLTE.setLayoutX(10);
//
//        lineChartLTE.setPrefWidth(850);
//        lineChartLTE.setMinWidth(850);
//        lineChartLTE.setMaxWidth(850);
//
//        lineChartLTE.setPrefHeight(575);
//        lineChartLTE.setMinHeight(575);
//        lineChartLTE.setMaxHeight(575);
//
//        lineChartLTE.setCreateSymbols(false); // убрать обводку точек на графике
//
//        lineChartLTE.setVisible(false);
//    }

    protected void buildInput() {
        btnCalculate = new Button("Calculate");
        btnCalculate.setLayoutX(500);
        btnCalculate.setLayoutY(600);
        btnCalculate.setMaxSize(70, 30);
        btnCalculate.setMinSize(70, 30);

        btnCalculate.setOnAction(actionEvent -> {
            double x0 = Double.valueOf(tbxX0.getText());
            double x = Double.valueOf(tbxX.getText());

            if (x0 <= 0 && x >= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Attention! Interval contains zero value!");
                alert.setContentText("");
                alert.showAndWait().ifPresent(rs -> {
                });
            }
            else {
                if (radioGroup.getSelectedToggle() == rbMain) {
                    drawLineChart();
                }
                else if (radioGroup.getSelectedToggle() == rbLTE) {
                    drawLineChartLTE();
                }
                else {
                    //
                }
            }
        });

        tbxX0 = new TextField();
        tbxX = new TextField();
        tbxY = new TextField();
        tbxN = new TextField();

        lblX0 = new Label("X_0");
        lblX = new Label("X");
        lblY = new Label("Y");
        lblN = new Label("N");

        tbxX0.setLayoutX(100);
        tbxX0.setLayoutY(600);
        tbxX0.setMaxSize(80, 30);
        tbxX0.setMinSize(80, 30);
        lblX0.setLayoutX(100);
        lblX0.setLayoutY(580);
        tbxX0.setText("1");

        tbxX.setLayoutX(200);
        tbxX.setLayoutY(600);
        tbxX.setMaxSize(80, 30);
        tbxX.setMinSize(80, 30);
        lblX.setLayoutX(200);
        lblX.setLayoutY(580);
        tbxX.setText("10");

        tbxY.setLayoutX(300);
        tbxY.setLayoutY(600);
        tbxY.setMaxSize(80, 30);
        tbxY.setMinSize(80, 30);
        lblY.setLayoutX(300);
        lblY.setLayoutY(580);
        tbxY.setText("2");

        tbxN.setLayoutX(400);
        tbxN.setLayoutY(600);
        tbxN.setMaxSize(80, 30);
        tbxN.setMinSize(80, 30);
        lblN.setLayoutX(400);
        lblN.setLayoutY(580);
        tbxN.setText("20");
    }

    private void buildPagination() {
        radioGroup = new ToggleGroup();
        rbMain = new RadioButton("Methods");
        rbMain.setLayoutX(700);
        rbMain.setLayoutY(590);
        rbMain.setToggleGroup(radioGroup);
        rbMain.fire();
        rbLTE = new RadioButton("Local Errors");
        rbLTE.setLayoutX(700);
        rbLTE.setLayoutY(610);
        rbLTE.setToggleGroup(radioGroup);
        rbGTE = new RadioButton("Global Errors");
        rbGTE.setLayoutX(700);
        rbGTE.setLayoutY(630);
        rbGTE.setToggleGroup(radioGroup);

        rbMain.setOnAction(actionEvent -> {
            drawLineChart();
        });

        rbLTE.setOnAction(actionEvent -> {
            drawLineChartLTE();
        });

    }

    private void initMethods() {
        double x0 = Double.valueOf(tbxX0.getText());
        double x = Double.valueOf(tbxX.getText());
        double y = Double.valueOf(tbxY.getText());
        double N = Double.valueOf(tbxN.getText());

        exactSolution = new ExactSolution(x0, x, y, N);
        exactSolution.solve();

        eulerSolution = new EulerSolution(x0, x, y, N);
        eulerSolution.solve();

        improvedEulerSolution = new ImprovedEulerSolution(x0, x, y, N);
        improvedEulerSolution.solve();

        rungeKuttaSolution = new RungeKuttaSolution(x0, x, y, N);
        rungeKuttaSolution.solve();
    }

    private void drawLineChart() {
        lineChart.getData().clear();

        initMethods();

        var pointsExact =
                PointsExtracter.makeXYChartSeries("Exact Solution", exactSolution.getxAxis(), exactSolution.getyAxis());
        var pointsEuler =
                PointsExtracter.makeXYChartSeries("Euler Solution", eulerSolution.getxAxis(), eulerSolution.getyAxis());
        var pointsImprovedEuler =
                PointsExtracter.makeXYChartSeries("Improved Euler Solution", improvedEulerSolution.getxAxis(), improvedEulerSolution.getyAxis());
        var pointsRungeKutta =
                PointsExtracter.makeXYChartSeries("Runge Kutta Solution", rungeKuttaSolution.getxAxis(), rungeKuttaSolution.getyAxis());

        lineChart.getData().add(pointsExact);
        lineChart.getData().add(pointsEuler);
        lineChart.getData().add(pointsImprovedEuler);
        lineChart.getData().add(pointsRungeKutta);
    }

    private void drawLineChartLTE() {
        lineChart.getData().clear();

        double x0 = Double.valueOf(tbxX0.getText());
        double x = Double.valueOf(tbxX.getText());
        double y = Double.valueOf(tbxY.getText());
        double N = Double.valueOf(tbxN.getText());

        initMethods();

        Error eulerError = new Error(x0, x, y, N);
        eulerError.calcError(eulerSolution);
        var pointsEulerErorr =
                PointsExtracter.makeXYChartSeries("Euler Error", eulerError.getxAxis(), eulerError.getyAxis());
        Error improvedEulerError = new Error(x0, x, y ,N);
        improvedEulerError.calcError(improvedEulerSolution);
        var pointsImpr = PointsExtracter.makeXYChartSeries("Improved Euler Error", improvedEulerError.getxAxis(), improvedEulerError.getyAxis());
        Error rungeKuttaError = new Error(x0, x, y ,N);
        rungeKuttaError.calcError(rungeKuttaSolution);
        var pointsRunge = PointsExtracter.makeXYChartSeries("Runge Kutta Error", rungeKuttaError.getxAxis(), rungeKuttaError.getyAxis());

        lineChart.getData().add(pointsEulerErorr);
        lineChart.getData().add(pointsImpr);
        lineChart.getData().add(pointsRunge);
    }
}