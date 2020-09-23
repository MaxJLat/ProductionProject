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
  public ChoiceBox labelChoiceBox;

  public void addProduct(ActionEvent actionEvent) throws SQLException {

    //connect to database, initializes a connection object inside the controller.
    DatabaseController.connectToDB();

    //fetch the connection
    Connection conn = DatabaseController.getConnection();

    //Prepared statement using 2 parameters
    PreparedStatement pstmt = DatabaseController.conn.prepareStatement(
        "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', ?, ? );");
    pstmt.setString(1, manufactorTF.getText());
    pstmt.setString(2, productNameTF.getText());
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
