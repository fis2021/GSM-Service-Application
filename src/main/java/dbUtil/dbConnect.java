package dbUtil;

import Config.Config;
import Exceptions.Not_A_DB_File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    private static Connection conn = null;


    public synchronized static Connection connect(String dbPath)   {
        try {
            // db parameters
            // create a connection to the database
            conn = DriverManager.getConnection(Config.SQCONN);


            // System.out.println("Connection to SQLite has been established.");

            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public synchronized static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}