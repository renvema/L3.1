package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
    private Connection connection;
    private final String DB_TYPE = "jdbc:mysql://";
    private final String DB_HOST = "localhost:";
    private final String DB_PORT = "3306/";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String MY_DB_TEST = "mydbtest";
    private final String LOGIN = "root";
    private final String PASSWORD = "13frog";
    private static final String TIME_ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=" +
            "true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public DBHandler() {
        this.connection = openConnection();
    }

    private Connection openConnection() {

        try {
            Driver driver = (Driver) Class.forName(DRIVER).newInstance();
            DriverManager.registerDriver(driver);
               return DriverManager.getConnection(DB_TYPE + DB_HOST
                    + DB_PORT + MY_DB_TEST + TIME_ZONE, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }
}
