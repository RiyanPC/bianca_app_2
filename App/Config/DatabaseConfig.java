package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static String url;
    private static String user;
    private static String pass;

    public static void init() {
        url = "jdbc:mysql://192.168.18.32/bd_10436374548";
        user = "bia";
        pass = "bianca";
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
