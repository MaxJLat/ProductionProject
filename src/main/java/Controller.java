import javafx.fxml.FXML;

import javafx.scene.control.Label;

import java.sql.*;


public class Controller {

  @FXML

  private Label lblOutput;


  public void sayHello() {

    lblOutput.setText("Hello FXML!");


  }


}