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

    protected TextField tbxN_MAX;
    protected Label lblN_MAX;

    protected RadioButton rbMain;
    protected RadioButton rbLTE;
    protected RadioButton rbGTE;
    protected ToggleGroup radioGroup;

    protected CheckBox chbxExact;
    protected CheckBox chbxEuler;
    protected CheckBox chbxImproved;
    protected CheckBox chbxRunge;

    ExactSolution exactSolution;
    EulerSolution eulerSolution;
    ImprovedEulerSolution improvedEulerSolution;
    RungeKuttaSolution rungeKuttaSolution;

    Error eulerError;
    Error improvedEulerError;
    Error rungeKuttaError;

    public MainView() {
        buildPagination();
        buildInput();
        buildLineChart();

        Group group = new Group(lineChart);
        group.getChildren().add(tbxX0);
        group.getChildren().add(tbxX);
        group.getChildren().add(tbxY);
        group.getChildren().add(tbxN);
        group.getChildren().add(tbxN_MAX);
        group.getChildren().add(lblX0);
        group.getChildren().add(lblX);
        group.getChildren().add(lblY);
        group.getChildren().add(lblN);
        group.getChildren().add(lblN_MAX);
        group.getChildren().add(btnCalculate);
        group.getChildren().add(rbGTE);
        group.getChildren().add(rbLTE);
        group.getChildren().add(rbMain);
        group.getChildren().add(chbxExact);
        group.getChildren().add(chbxEuler);
        group.getChildren().add(chbxImproved);
        group.getChildren().add(chbxRunge);
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
                    drawLineChartGTE();
                }
            }
        });

        tbxX0 = new TextField();
        tbxX = new TextField();
        tbxY = new TextField();
        tbxN = new TextField();
        tbxN_MAX = new TextField();

        lblX0 = new Label("X_0");
        lblX = new Label("X");
        lblY = new Label("Y");
        lblN = new Label("N");
        lblN_MAX = new Label("N_MAX");

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

        tbxN_MAX.setLayoutX(400);
        tbxN_MAX.setLayoutY(660);
        tbxN_MAX.setMaxSize(80, 30);
        tbxN_MAX.setMinSize(80, 30);
        tbxN_MAX.setDisable(true);
        lblN_MAX.setLayoutX(400);
        lblN_MAX.setLayoutY(640);
        tbxN_MAX.setText("200");
    }

    private void buildPagination() {
        radioGroup = new ToggleGroup();
        rbMain = new RadioButton("Methods");
        rbMain.setLayoutX(630);
        rbMain.setLayoutY(590);
        rbMain.setToggleGroup(radioGroup);
        rbMain.fire();
        rbLTE = new RadioButton("Local Errors");
        rbLTE.setLayoutX(630);
        rbLTE.setLayoutY(610);
        rbLTE.setToggleGroup(radioGroup);
        rbGTE = new RadioButton("Global Errors");
        rbGTE.setLayoutX(630);
        rbGTE.setLayoutY(630);
        rbGTE.setToggleGroup(radioGroup);

        rbMain.setOnAction(actionEvent -> {
            chbxExact.setDisable(false);
            tbxN_MAX.setDisable(true);
            lineChart.getXAxis().setLabel("x Axis");
            drawLineChart();
        });

        rbLTE.setOnAction(actionEvent -> {
            chbxExact.setDisable(true);
            tbxN_MAX.setDisable(true);
            lineChart.getXAxis().setLabel("x Axis");
            drawLineChartLTE();
        });

        rbGTE.setOnAction(actionEvent -> {
            chbxExact.setDisable(true);
            tbxN_MAX.setDisable(false);
            lineChart.getXAxis().setLabel("N Axis");
            drawLineChartGTE();
        });

        chbxExact = new CheckBox("Exact");
        chbxExact.setLayoutX(750);
        chbxExact.setLayoutY(590);
        chbxExact.setSelected(true);
        chbxEuler = new CheckBox("Euler");
        chbxEuler.setLayoutX(750);
        chbxEuler.setLayoutY(610);
        chbxEuler.setSelected(true);
        chbxImproved = new CheckBox("Improved");
        chbxImproved.setLayoutX(750);
        chbxImproved.setLayoutY(630);
        chbxImproved.setSelected(true);
        chbxRunge = new CheckBox("Runge Kutta");
        chbxRunge.setLayoutX(750);
        chbxRunge.setLayoutY(650);
        chbxRunge.setSelected(true);

        chbxExact.setOnAction(e -> {
            btnCalculate.fire();
        });

        chbxEuler.setOnAction(e -> {
            btnCalculate.fire();
        });

        chbxImproved.setOnAction(e -> {
            btnCalculate.fire();
        });

        chbxRunge.setOnAction(e -> {
            btnCalculate.fire();
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

        /// ERRORS

        eulerError = new Error(x0, x, y, N);
        eulerError.calcError(eulerSolution);

        improvedEulerError = new Error(x0, x, y ,N);
        improvedEulerError.calcError(improvedEulerSolution);

        rungeKuttaError = new Error(x0, x, y ,N);
        rungeKuttaError.calcError(rungeKuttaSolution);
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

        if (chbxExact.isSelected()) lineChart.getData().add(pointsExact);
        if (chbxEuler.isSelected()) lineChart.getData().add(pointsEuler);
        if (chbxImproved.isSelected()) lineChart.getData().add(pointsImprovedEuler);
        if (chbxRunge.isSelected()) lineChart.getData().add(pointsRungeKutta);
    }

    private void drawLineChartLTE() {
        lineChart.getData().clear();

        double x0 = Double.valueOf(tbxX0.getText());
        double x = Double.valueOf(tbxX.getText());
        double y = Double.valueOf(tbxY.getText());
        double N = Double.valueOf(tbxN.getText());

        initMethods();


        var pointsEulerErorr =
                PointsExtracter.makeXYChartSeries("Euler Error", eulerError.getxAxis(), eulerError.getyAxis());
        var pointsImpr = PointsExtracter.makeXYChartSeries("Improved Euler Error", improvedEulerError.getxAxis(), improvedEulerError.getyAxis());
        var pointsRunge = PointsExtracter.makeXYChartSeries("Runge Kutta Error", rungeKuttaError.getxAxis(), rungeKuttaError.getyAxis());

        if (chbxEuler.isSelected()) lineChart.getData().add(pointsEulerErorr);
        if (chbxImproved.isSelected()) lineChart.getData().add(pointsImpr);
        if (chbxRunge.isSelected()) lineChart.getData().add(pointsRunge);
    }

    private void drawLineChartGTE() {
        lineChart.getData().clear();

        double x0 = Double.valueOf(tbxX0.getText());
        double x = Double.valueOf(tbxX.getText());
        double y = Double.valueOf(tbxY.getText());
        double N = Double.valueOf(tbxN.getText());
        double N_MAX = Double.valueOf(tbxN_MAX.getText());

        initMethods();

        // TODO
        GlobalError eulerGError = new GlobalError(x0, x, y, N, N_MAX);
        eulerGError.calcError(eulerError, eulerSolution);
        var pointsEulerErorr =
                PointsExtracter.makeXYChartSeries("Euler Global Error", eulerGError.getxAxis(), eulerGError.getyAxis());
        GlobalError improvedEulerGError = new GlobalError(x0, x, y ,N, N_MAX);
        improvedEulerGError.calcError(improvedEulerError, improvedEulerSolution);
        var pointsImpr = PointsExtracter.makeXYChartSeries("Improved Euler Global Error", improvedEulerGError.getxAxis(), improvedEulerGError.getyAxis());
        GlobalError rungeKuttaGError = new GlobalError(x0, x, y ,N, N_MAX);
        rungeKuttaGError.calcError(rungeKuttaError, rungeKuttaSolution);
        var pointsRunge = PointsExtracter.makeXYChartSeries("Runge Kutta Global Error", rungeKuttaGError.getxAxis(), rungeKuttaGError.getyAxis());

        if (chbxEuler.isSelected()) lineChart.getData().add(pointsEulerErorr);
        if (chbxImproved.isSelected()) lineChart.getData().add(pointsImpr);
        if (chbxRunge.isSelected()) lineChart.getData().add(pointsRunge);
    }
}