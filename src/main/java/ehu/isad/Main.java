package ehu.isad;

import ehu.isad.controllers.ui.HasieraController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
  private Parent HasieraController;
  private Stage stage;
  private Scene sceneHas;
  private HasieraController hasieraController;

  @Override
  public void start(Stage primaryStage) throws Exception{

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("Captchak");
    stage.setScene(sceneHas);
    stage.show();
  }

  private void pantailakKargatu() throws IOException {
    FXMLLoader loaderHasiera = new FXMLLoader(getClass().getResource("/sample.fxml"));
    HasieraController = (Parent) loaderHasiera.load();
    hasieraController = loaderHasiera.getController();
    hasieraController.setMainApp(this);
    sceneHas = new Scene(HasieraController);
  }


  public static void main(String[] args) {
    launch(args);
  }
}
