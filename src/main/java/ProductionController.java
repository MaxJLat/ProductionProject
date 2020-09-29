import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class ProductionController {

  public ComboBox<Integer> quantityComboBox;
  public ListView<Product> productListFXML;

  public void initialize() {

    //Assigns products observable list to the productListFXML listview.
    // Uses the toString() methods to print to the ListView.
    productListFXML.setItems(Controller.products);

    //Populate quantityComboBox with choices 1-10 upon initialization.
    for (int i = 1; i < 11; i++) {
      quantityComboBox.getItems().add(i);
    }
    quantityComboBox.setEditable(true);
    quantityComboBox.getSelectionModel().selectFirst();

  }

  //Event handler for pressing the record production button.
  public void recordProduction(ActionEvent actionEvent) throws SQLException {

    //Gets the selected product from the productListFXML.
    Product selectedProduct =
        productListFXML.getSelectionModel().getSelectedItem();

    //Connect to Database and fetch the Connection.
    DatabaseController.connectToDB();
    Connection conn = DatabaseController.getConnection();
    PreparedStatement pstmt;

    int itemCount = quantityComboBox.getValue();

    //create a ProductionRecord from product and itemCount.
    ProductionRecord productRecord = new ProductionRecord(selectedProduct, itemCount);

    Date sqlDate = new Date(productRecord.getProdDate().getTime());

    // Prepared Statement to insert into database.
    pstmt = conn.prepareStatement("INSERT INTO PRODUCTIONRECORD VALUES(?,?,?,?)");
    //Filling the prepared statement.
    pstmt.setInt(1, productRecord.getProductionNum());
    pstmt.setInt(2, productRecord.getProductID());
    pstmt.setString(3, productRecord.getSerialNum());
    pstmt.setDate(4, sqlDate);
    //execute prepared statement.
    pstmt.executeUpdate();


    System.out.println("Record production button was pressed.");
  }
}
