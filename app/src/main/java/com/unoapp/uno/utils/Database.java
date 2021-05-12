package com.unoapp.uno.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import com.unoapp.uno.models.PlayerInfo;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author sqlitetutorial.net
 */
public class Database {

    private Connection conn;

    /**
    * Connect to a userdata database
     * @throws IOException
     * @throws SQLException
    */
    public void connect() throws IOException, SQLException {
        String url = "jdbc:sqlite:" + getDatabaseFile();
        conn = DriverManager.getConnection(url);
    }

    private String getDatabaseFile() throws IOException {
        Path userdata = Paths.get(Paths.get(System.getProperty("user.home")).toAbsolutePath().toString(), "unoapp");
        if (!Files.exists(userdata)) {
            Files.createDirectories(userdata);
        }
        return Paths.get(userdata.toString(), "userdata.db").toString();
    }

    public void migrate() throws DBNotInitializedException, FileNotFoundException {
        if (this.conn == null) {
            throw new DBNotInitializedException();
        }
        for (File file : globMigrations()) {
            ScriptRunner runner = new ScriptRunner(this.conn);
            // Not implemented by sqlite driver
            runner.setEscapeProcessing(false);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            runner.runScript(reader);
        }
    }

    public ArrayList<PlayerInfo> getAllPlayers() {
        ArrayList<PlayerInfo> players = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery("SELECT * FROM players");
            players = parseResultSet(set);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }

    public void createPlayer(String name) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("INSERT INTO players (id, name) VALUES (?, ?)");
        prep.setString(1, UUID.randomUUID().toString());
        prep.setString(2, name);
        prep.executeUpdate();
        prep.close();
        conn.commit();
    }

    private ArrayList<PlayerInfo> parseResultSet(ResultSet set) throws SQLException {
        ArrayList<PlayerInfo> players = new ArrayList<>();
        while (set.next()) {
            players.add(new PlayerInfo(set.getString("id"), set.getString("name"), set.getInt("won"),
                    set.getInt("lost"), set.getInt("experience")));
        }
        return players;
    }

    private File[] globMigrations() {
        File dir = new File(Paths.get(Constants.getAssetsDirectory().toString(), "migrations").toString());
        return dir.listFiles((d, name) -> name.endsWith(".sql"));
    }

    public class DBNotInitializedException extends Exception {
        DBNotInitializedException() {
            super("Database not initialized");
        }
    }
}