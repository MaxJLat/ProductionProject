import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseController {

  //Database Credentials Temporary
  final String USER = "";
  final String PASS = "";
  public static Connection conn = null;

  /*Opens a connection object to the database*/
  public static void connectToDB() throws IOException {
    Properties prop = new Properties();
    prop.load(new FileInputStream("C:\\Users\\Maximilien Latura\\IdeaProjects\\ProductionProject\\src\\main\\java\\password.properties"));
    String PASS = prop.getProperty("password");
    //File locations for database and h2 driver.
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/ProductionLineDB";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //Class.forName(new org.h2.Driver());

      //STEP 2: Open a connection
      //conn = DriverManager.getConnection(DB_URL, USER, PASS);
      conn = DriverManager.getConnection(DB_URL, "", "efgh");


    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();

    }

  }

  /*method to fetch the connection outside of DatabaseController so that the Controllers
  can use it to create statements.
  @return the connection object.*/
  public static Connection getConnection() {
    return conn;
  }

  /*Closes the connection made by connectToDB().*/
  public static void closeDB() {
    try {
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
