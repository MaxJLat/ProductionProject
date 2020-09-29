import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TextArea;

public class ProductionLogController {

  public TextArea productionRecordTA;

  public void initialize() throws SQLException {
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
}
