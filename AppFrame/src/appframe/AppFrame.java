package appframe;



import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AppFrame extends Application {

    Stage stage;

    public AppFrame(Stage st) {
        stage = st;
    }

    Scene scene = new Scene(new Pane());

    ImageView logoImage = new ImageView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage bufferStage) throws Exception {
        stageConfigCaller().start(stage);
        //LogoImage();
    }

    public StageConfigs stageConfigCaller() throws Exception {
        StageConfigs obj = new StageConfigs(stage, scene);
        return obj;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void LoginAnimator(Property p, double changeFactor, double bounceCorrection) throws Exception {
        System.out.println(logoImage.getFitWidth());
        final Timeline timeline = new Timeline();
        final KeyValue Movement1 = new KeyValue(p, changeFactor + stageConfigCaller().bounceMeter + bounceCorrection);
        final KeyFrame Movement1KeyFrame = new KeyFrame(Duration.millis(stageConfigCaller().timer), Movement1);
        final KeyValue Movement2 = new KeyValue(p, changeFactor);
        final KeyFrame Movement2KeyFrame = new KeyFrame(Duration.millis(stageConfigCaller().timer + stageConfigCaller().bounceDelay), Movement2);
        timeline.getKeyFrames().add(Movement1KeyFrame);
        timeline.getKeyFrames().add(Movement2KeyFrame);
        timeline.play();
    }

    public void leftRightDraggerProp() throws Exception {
        System.out.print("Hello");

        LoginAnimator(logoImage.xProperty(), 100, 0);
        LoginAnimator(logoImage.fitWidthProperty(), ((stage.getWidth()) - (stageConfigCaller().rightOffset + stageConfigCaller().leftOffset)), 0);
    }

}
