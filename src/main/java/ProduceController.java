import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class ProduceController {

  public ComboBox quantityComboBox;

  public void initialize() {

    for (int i = 1; i < 11; i++) {
      quantityComboBox.getItems().add(i);
    }
    quantityComboBox.setEditable(true);
    quantityComboBox.getSelectionModel().selectFirst();

  }

  public void recordProduction(ActionEvent actionEvent) {
    System.out.println("Record production button was pressed.");
  }
}
