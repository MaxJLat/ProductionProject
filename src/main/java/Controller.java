import javafx.fxml.FXML;

import javafx.scene.control.Label;


public class Controller {

  public void initialize(){

  }


  @FXML

  private Label lblOutput;


  public void sayHello() {

    lblOutput.setText("Hello FXML!");


  }


}