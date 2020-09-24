import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class ProduceController {

  public ComboBox quantityComboBox;

  public void initialize() {

    //Populate quantityComboBox with choices 1-10 upon initialization.
    for (int i = 1; i < 11; i++) {
      quantityComboBox.getItems().add(i);
    }
    quantityComboBox.setEditable(true);
    quantityComboBox.getSelectionModel().selectFirst();

  }

  //Event handler for pressing the record production button.
  public void recordProduction(ActionEvent actionEvent) {
    System.out.println("Record production button was pressed.");
  }
}
