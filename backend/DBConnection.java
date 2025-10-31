import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Change URL if your DB uses a different host/port/service
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "YOUR_DB_USERNAME";      // <-- change this
    private static final String PASSWORD = "YOUR_DB_PASSWORD";  // <-- change this

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC driver not found. Put ojdbc8.jar on classpath.");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
