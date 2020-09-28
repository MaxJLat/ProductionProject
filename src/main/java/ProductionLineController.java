import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProductionLineController {

  @FXML
  private TextField productNameTF;
  @FXML
  private TextField manufactorTF;
  @FXML
  private ChoiceBox<ItemType> labelChoiceBox;
  @FXML
  private TableView<Product> productLineTable;
  @FXML
  private TableColumn<?, ?> nameColumn;
  @FXML
  private TableColumn<?, ?> typeColumn;
  @FXML
  private TableColumn<?, ?> manuColumn;

//Observable List is created in Controller as public static, accessible by all Controllers for now.


  //initialize runs on startup
  public void initialize() throws SQLException {

    //assigns ObservableList products to the table.
    productLineTable.setItems(Controller.products);

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
              null, null));
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

  /*
   addProduct eventhandler for pressing the button.
   takes input from the user in the textfields productNameTF, manufactorTF,
   and eventually the labelChoiceBox. it puts the inputs into the product table
   in the database. Afterwards it returns the entire product table name, type, and manufactorer.
   */
  public void addProduct(ActionEvent actionEvent) throws SQLException {

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
      case VISUAL:
        Controller.products.add(new MoviePlayer(productNameTF.getText(), manufactorTF.getText(),
            null, null));
        break;
      default:
        System.out.println("incompatible.");

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
        stmt.close();
        conn.close();

    }
  }
}
