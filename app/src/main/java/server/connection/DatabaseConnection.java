package server.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private final String server = "localhost";
    private final String port = "3306";
    private final String database = "chatsystem";
    private final String userName = "admin";
    private final String password = "*Nghia1692004";

    // Private constructor to prevent instantiation
    private DatabaseConnection() { }

    // Thread-safe getInstance() using synchronized block
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Connects to the database, initializing connection if null or closed
    public void connectToDatabase() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
                System.out.println("Database connection established.");
            } catch (SQLException e) {
                System.err.println("Failed to connect to the database: " + e.getMessage());
                throw e; // Rethrow the exception after logging it
            }
        }
    }

    // Getter for connection
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connectToDatabase();
        }
        return connection;
    }

    // Manually close the connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }
}
