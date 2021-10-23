import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;

public class Main extends Application{

    private MainView mainView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        mainView = new MainView();
        stage.setScene(mainView.getScene());
        stage.setTitle("Differential Equation Solver");
        stage.setWidth(900);
        stage.setHeight(730);
        stage.setResizable(false);
        stage.show();
    }
}