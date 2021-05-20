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

import com.unoapp.uno.models.Achievement;
import com.unoapp.uno.models.PlayerInfo;
import com.unoapp.uno.utils.Constants.AchievementTypes;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * Basic database operations
 * 
 */
public class Database {

    /**
     * Connection to database
     */
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

    /**
     * Get path to database
     * Returns a path in OS's data directory
     * Maybe user specific
     * 
     * @return Path of database
     * @throws IOException
     */
    private String getDatabaseFile() throws IOException {
        Path userdata = Paths.get(Paths.get(System.getProperty("user.home")).toAbsolutePath().toString(), "unoapp");
        if (!Files.exists(userdata)) {
            Files.createDirectories(userdata);
        }
        return Paths.get(userdata.toString(), "userdata.db").toString();
    }

    /**
     * Carries out migrations specified in assets/migrations
     * 
     * @throws DBNotInitializedException if Database is not initialized prior to migration
     * @throws FileNotFoundException if migration file isn't found
     */
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

    /**
     * Retrieves details of all players from database
     * 
     * @return ArrayList of PlayerInfo
     */
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

    /**
     * Retrieves details of single player
     * 
     * @param id ID of player to find
     * @return PlayerInfo with details of player
     */
    public PlayerInfo getSinglePlayer(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM players WHERE id = ?");
            stmt.setString(1, id);
            ResultSet set = stmt.executeQuery();
            ArrayList<PlayerInfo> info = parseResultSet(set);
            if (info.size() > 0) {
                return info.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create new player
     * 
     * @param name Name of player to be created
     * @throws SQLException
     */
    public void createPlayer(String name) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("INSERT INTO players (id, name) VALUES (?, ?)");
        prep.setString(1, UUID.randomUUID().toString());
        prep.setString(2, name);
        prep.executeUpdate();
        prep.close();
        conn.commit();
    }

    /**
     * Update player table
     * 
     * @param info Updated player details
     * @throws SQLException 
     */
    public void updatePlayer(PlayerInfo info) throws SQLException {
        PreparedStatement prep = conn.prepareStatement(
                "UPDATE players SET won = ?, lost = ?, experience = ?, draw2_count = ?, draw4_count = ?, skip_count = ?, rev_count = ?, wild_count = ? WHERE id = ?");
        prep.setInt(1, info.getGamesWon());
        prep.setInt(2, info.getGamesLost());
        prep.setInt(3, info.getExperience());
        prep.setInt(4, info.getDraw2());
        prep.setInt(5, info.getDraw4());
        prep.setInt(6, info.getSkip());
        prep.setInt(7, info.getReverse());
        prep.setInt(8, info.getWild());
        prep.setString(9, info.getId());
        prep.executeUpdate();
        prep.close();
        conn.commit();
    }

    /**
     * Parses result set into ArrayList of PlayerInfo
     * 
     * @param set ResultSet retrieved after executing query
     * @return ArrayList of PlayerInfo
     * @throws SQLException
     */
    private ArrayList<PlayerInfo> parseResultSet(ResultSet set) throws SQLException {
        ArrayList<PlayerInfo> players = new ArrayList<>();
        while (set.next()) {
            players.add(new PlayerInfo(set.getString("id"), set.getString("name"), set.getInt("won"),
                    set.getInt("lost"), set.getInt("experience"), set.getInt("draw2_count"), set.getInt("draw4_count"),
                    set.getInt("skip_count"), set.getInt("rev_count"), set.getInt("wild_count")));
        }
        return players;
    }

    /**
     * Get all achievements
     * 
     * @return ArrayList of all achievements
     */
    public ArrayList<Achievement> getAchievements() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM achievements");
            ResultSet set = stmt.executeQuery();
            var parsed = parseAchievements(set);
            stmt.close();
            return parsed;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse ResultSet into Achievement model
     * 
     * @param set ResultSet returned from sql query
     * @return ArrayList of achievements parsed
     * @throws SQLException
     */
    private ArrayList<Achievement> parseAchievements(ResultSet set) throws SQLException {
        ArrayList<Achievement> achievements = new ArrayList<>();
        while (set.next()) {
            achievements.add(new Achievement(AchievementTypes.valueOf(set.getString("typee")), set.getString("title"),
                    set.getFloat("total")));
        }
        return achievements;
    }

    /**
     * Glob all files ending with .sql (Hoping they all contain legitimate code. Technically anyone can inject anything but whatever)
     * @return Array of File(s) globed
     */
    private File[] globMigrations() {
        File dir = new File(Paths.get(Assets.getAssetsDirectory().toString(), "migrations").toString());
        return dir.listFiles((d, name) -> name.endsWith(".sql"));
    }

    /**
     * Custom exception when database isnt initialize where it should be
     */
    public class DBNotInitializedException extends Exception {
        DBNotInitializedException() {
            super("Database not initialized");
        }

    }
}