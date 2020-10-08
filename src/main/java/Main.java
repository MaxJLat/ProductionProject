
import java.lang.ModuleLayer.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override

  public void start(Stage primaryStage) throws Exception {

    FXMLLoader fxmlLoader = new FXMLLoader();

    Parent root = fxmlLoader.load(getClass().getResource("sample.fxml"));

    Controller controller = fxmlLoader.getController();

    Scene scene = new Scene(root, 300, 275);

    primaryStage.setTitle("FXML Welcome");

    primaryStage.setScene(scene);

    // Code to add the style sheet to the FXML files
    scene.getStylesheets().add(Main.class.getResource("ProductionStyleSheet.css").toExternalForm());

    primaryStage.show();


  }
}
