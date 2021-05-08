package com.unoapp.uno.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sqlitetutorial.net
 */
public class Database {
     /**
     * Connect to a sample database
     */
    public static void connect() {
        
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/mnt/d/test/database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

}
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:/mnt/d/test/database.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS achievments (\n"
                + " id integer PRIMARY KEY,\n"
                + "	games_played integer,\n"
                + "	games_won integer,\n"
                + "	games_lost integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}