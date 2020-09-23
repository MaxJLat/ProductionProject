import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

  //Database Credentials Temporary
  final String USER = "";
  final String PASS = "";
  public static Connection conn = null;

  public static void connectToDB() {
    //File locations for database and h2 driver.
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/ProductionLineDB";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //Class.forName(new org.h2.Driver());

      //STEP 2: Open a connection
      //conn = DriverManager.getConnection(DB_URL, USER, PASS);
      conn = DriverManager.getConnection(DB_URL);

      //STEP 3: Execute a query

    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();

    }

  }

  public static Connection getConnection() {
    return conn;
  }

  public static void closeDB() {
    try {
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
