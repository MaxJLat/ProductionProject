import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

  public static void connectToDB() {
    //File locations for database and h2 driver.
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/ProductionLineDB";

    //Database Credentials Temporary
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //Class.forName(new org.h2.Driver());

      //STEP 2: Open a connection
      //conn = DriverManager.getConnection(DB_URL, USER, PASS);
      conn = DriverManager.getConnection(DB_URL);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      stmt.close();
      conn.close();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();

    }

  }
}
