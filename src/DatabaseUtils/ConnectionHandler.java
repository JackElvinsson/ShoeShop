package DatabaseUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionHandler {
    private static Connection con;

    public static Connection getConnection() {

        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream("src/properties.properties"));

            con = DriverManager.getConnection(
                    properties.getProperty("connectionString"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));

        } catch (SQLException ex) {

            System.out.println("Failed to create the database connection.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return con;

    }

}