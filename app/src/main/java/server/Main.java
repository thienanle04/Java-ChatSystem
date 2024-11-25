package server;

import java.sql.SQLException;

import server.connection.DatabaseConnection;
import server.service.Service;

public class Main {
    public static void main(String args[]) {
        try {
            DatabaseConnection.getInstance().connectToDatabase();
            Service.getInstance().startServer();
        } catch (SQLException e) {
            System.out.println("Error : " + e + "\n");
        }
    };
}