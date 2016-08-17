package appframe;



import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage bufferStage) throws Exception {
        frameCaller();
    }

    public void frameCaller() throws Exception {
        AppFrame obj = new AppFrame(stage);
        obj.start(stage);
    }
}
