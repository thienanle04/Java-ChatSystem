package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnect{

    public static void main(String[] args) {
        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/chatsystem";
        String username = "JavaChatSystem";
        String password = "javachatsystem";

        Connection connection = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the MySQL database successfully!");

            // Create a statement object to execute queries
            Statement statement = connection.createStatement();

            // Execute a query and get the result set
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            // Print the column names
            

            // Process the result set
            while (resultSet.next()) {
                System.out.println("Username: " + resultSet.getString("username"));
                // Access other columns as needed
            }

        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
