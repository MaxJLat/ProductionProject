import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class ProductionLineController {

  public TextField productNameTF;
  public TextField manufactorTF;
  public ChoiceBox<ItemType> labelChoiceBox;

  //initialize runs on startup
  public void initialize() {



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
      System.out.println(rset.getString("name") + " " + rset.getString("manufacturer") + " " + rset
          .getString("type"));
    }
    stmt.close();
    conn.close();

  }
}
