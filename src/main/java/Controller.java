import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;


public class Controller {
  //ObservableList that contains all products, accessible by all controllers.
  // Consider making getters and setters.
  public static ObservableList<Product> products = FXCollections.observableArrayList();

  public void initialize(){

  }


  @FXML

  private Label lblOutput;


  public void sayHello() {

    lblOutput.setText("Hello FXML!");


  }


}