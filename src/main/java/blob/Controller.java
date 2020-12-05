package blob;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {


  //ObservableList that contains all products, accessible by all controllers.
  // Consider making getters and setters.
  public static ObservableList<Product> products = FXCollections.observableArrayList();
  //List of FXML fields.
  public TextField productNameTF;
  public TextField manufactorTF;
  public ChoiceBox labelChoiceBox;
  public TableView productLineTable;
  public TableColumn nameColumn;
  public TableColumn typeColumn;
  public TableColumn manuColumn;
  public ListView<Product> productListFXML;
  public ComboBox<Integer> quantityComboBox;
  public Button recordProductionBttn;
  public TextArea productionRecordTA;
  private Properties prop;

  //Test screen for use to populate products
  Screen testScreen = new Screen();

  /*initialize method runs on program startup.*/
  public void initialize() throws SQLException, IOException {

    productionTabInitialize();

    productionLineTabInitialize();

    productionLogInitialize();


  }

  /*Add product method, activates when pressing the add product button.
   * It takes the values in the labelChoiceBox, productNameTF, manufactorTF and creates products
   * from them.
   * @param Add product triggers on pressing the add product button in the GUI through javafx event handler.
   * @return returns void, generates a product from the filled text fields and puts it into the database. */
  public void addProduct(ActionEvent actionEvent) throws SQLException, IOException {

    //First add to Products observable list.
    //assign the ItemType to a tempItem variable for readability and to send through switch
    ItemType tempItem = (ItemType) labelChoiceBox.getValue();
    //switch statement that determines what type of object to create based on tempItem value.
    //Adds them to products observable list that will then add to the table.
    switch (tempItem) {
      case AUDIO:
        Controller.products.add(new AudioPlayer(productNameTF.getText(), manufactorTF.getText(),
            null, null));

        break;
      //hard coded screen and monitor type for now until more user input is able to be used.
      case VISUAL:
        Controller.products.add(new MoviePlayer(productNameTF.getText(), manufactorTF.getText(),
            testScreen, MonitorType.LCD));
        break;
      default:
        System.out.println("incompatible.");
    }

    //connect to database, initializes a connection object inside the controller.
    DatabaseController.connectToDB();

    //fetch the connection
    Connection conn = DatabaseController.getConnection();

    //Prepared statement using 2 parameters
    PreparedStatement pstmt = DatabaseController.conn.prepareStatement(
        "INSERT INTO Product(type, manufacturer, name) VALUES ( ?, ?, ? );");
    //Get labelChoiceBox selection, the method returns an object
    //ItemType is an object by inheritance
    // but explicitly cast to ItemType to make easier to read
    ItemType temp = (ItemType) labelChoiceBox.getValue();
    //then use the temp variable to hold this ItemType
    //Use .toString to retrieve the TYPE as a String.
    pstmt.setString(1, temp.toString());
    pstmt.setString(2, manufactorTF.getText());
    pstmt.setString(3, productNameTF.getText());

    //executeUpdate() since i don't need anything back.
    pstmt.executeUpdate();
    // close the prepared statement
    pstmt.close();
    //create a statement to retrieve the entire table.
    Statement stmt = conn.createStatement();
    //using executeQuery() to get a return of a resultset.
    ResultSet rset = stmt.executeQuery("SELECT name, type, manufacturer FROM PRODUCT");
    //looping through resultset and pulling columns by label for the data i need.
    while (rset.next()) {
      System.out
          .println(rset.getString("name") + " " + rset.getString("manufacturer") + " " + rset
              .getString("type"));
    }
    //close database connections.
    rset.close();
    stmt.close();
    conn.close();
    DatabaseController.closeDB();


  }

  /*recordProduction method is an eventhandler that activates when the recordProduction is pressed.
   * It takes the selection from the productListFXML and creates a productionRecord, then it
   * uploads the productionRecord to the database.
   * @param Event handler for when pressing the add production record button.
   * @return Returns void, generates a production record from the selected product, adds to database.*/
  public void addProductionRecord(ActionEvent actionEvent) throws SQLException, IOException {

    //Gets the selected product from the productListFXML.
    Product selectedProduct =
        productListFXML.getSelectionModel().getSelectedItem();

    //Connect to Database and fetch the Connection.
    DatabaseController.connectToDB();
    Connection conn = DatabaseController.getConnection();
    PreparedStatement pstmt;

    //quantityComboBox returns a string because the combobox is editable.
    //String.valueOf() is unnecessary but makes it more explicit a String is being passed here.
    int itemCount = Integer.parseInt(String.valueOf(quantityComboBox.getValue()));

    //create a ProductionRecord from product and itemCount.
    for (int i = itemCount; i > 0; i--) {
      ProductionRecord productRecord = new ProductionRecord(selectedProduct, itemCount);
      //get the date in sql format.
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
    }

    System.out.println("Record production button was pressed.");

    //Updates the productionlog with the new added ProductionRecord from database.
    updateProductionLog();

  }


  /*Production tab initialize
  * @return Returns void, an organization method to keep the code for tabs separate.*/
  private void productionTabInitialize() {
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


  /*productionLineTab initialize method
  * @return Returns void, an organization method to keep the code for tabs separate. */
  private void productionLineTabInitialize() throws SQLException, IOException {
    //assigns ObservableList products to the table.
    productLineTable.setItems(products);

    //Make sure to put inside a method

    //setCellValueFactor requires Getters of the field to exist.
    //assigns the field values to the columns, but not the headers.
    nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
    manuColumn.setCellValueFactory(new PropertyValueFactory("manufacturer"));

    //Connect to database and select NAME, TYPE, and MANUFACTURER from PRODUCT table in DB.
    DatabaseController.connectToDB();
    Connection conn = DatabaseController.getConnection();
    ResultSet rset = conn.createStatement().executeQuery("SELECT NAME,"
        + " TYPE, MANUFACTURER FROM PRODUCT");

    //Assign the resultset values to the products observable list.
    while (rset.next()) {
      ItemType tempItem = ItemType.valueOf(rset.getString("TYPE"));
      //Use a switch statement on tempItem to determine what SubClass of Product to create.
      //Based on the ItemType.
      switch (tempItem) {
        case AUDIO:
          Controller.products.add(new AudioPlayer(rset.getString("NAME"),
              rset.getString("MANUFACTURER"),
              null, null));

          break;
        case VISUAL:
          Controller.products.add(new MoviePlayer(rset.getString("NAME"),
              rset.getString("MANUFACTURER"),
              testScreen, MonitorType.LCD));
          break;
        default:
          System.out.println("incompatible.");
      }
    }

    //For looping through enum values and populating the labelChoiceBox with them.
    for (ItemType item : ItemType.values()) {
      labelChoiceBox.getItems().add(item);
    }
  }

  /*productionLog initialize method
  * @return Returns void, an organization method to keep the code for tabs separate.*/
  private void productionLogInitialize() throws SQLException, IOException {
    //Make connection object inside DatabaseController.
    DatabaseController.connectToDB();

    //Create a copy of the connection inside ProductionLogController.
    Connection conn = DatabaseController.getConnection();

    //Create a Statement, and get a ResultSet by executeQuery().
    Statement stmt = conn.createStatement();
    ResultSet rset = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");

    while (rset.next()) {
      ProductionRecord tempProRec = new ProductionRecord(
          rset.getInt("PRODUCTION_NUM"),
          rset.getInt("PRODUCT_ID"),
          rset.getString("SERIAL_NUM"),
          rset.getDate("DATE_PRODUCED")
      );

      productionRecordTA.setText(productionRecordTA.getText() + "\n" + tempProRec.toString());
    }
    rset.close();
    conn.close();
    DatabaseController.closeDB();

  }

  /*
  Updates the production log table when called, by pulling from the Database.
   @return Returns void, used to force the production log table to update from database.*/
  private void updateProductionLog() throws SQLException, IOException {

    //Clear the current ProductionRecord textArea
    productionRecordTA.setText("");

    //Make connection object inside DatabaseController.
    DatabaseController.connectToDB();

    //Create a copy of the connection inside ProductionLogController.
    Connection conn = DatabaseController.getConnection();

    //Create a Statement, and get a ResultSet by executeQuery().
    Statement stmt = conn.createStatement();
    ResultSet rset = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");

    //get the arguments from the database for a productionRecord constructor.
    while (rset.next()) {
      ProductionRecord tempProRec = new ProductionRecord(
          rset.getInt("PRODUCTION_NUM"),
          rset.getInt("PRODUCT_ID"),
          rset.getString("SERIAL_NUM"),
          rset.getDate("DATE_PRODUCED")
      );

      //populate the productionRecordTA.
      productionRecordTA.setText(productionRecordTA.getText() + "\n" + tempProRec.toString());
    }
    rset.close();
    conn.close();
    DatabaseController.closeDB();

  }


}