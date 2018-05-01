package com.bit.telebot;

import java.sql.*;

public class Database {

    private static Database db = null;

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    private final String DB_NAME = "jdbc:sqlite:database.db";
    private final String TABLE_GAME = "game";
    private final String TABLE_ADMIN = "admin";

    public Database() {
        createDatabase();
        createGameTable();
        createAdminTable();
        addDev("OGBittle");
        addDev("Bit_assesive");
    }

    private void createDatabase() {

        try {
            DriverManager.getConnection(DB_NAME);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createGameTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_GAME + "("
                + "	username text NOT NULL UNIQUE,\n"
                + "	type_score INTEGER NOT NULL,\n"
                + " scramble_score INTEGER NOT NULL,\n"
                + " taboo_score INTEGER NOT NULL,\n"
                + " guess_score INTEGER NOT NULL,\n"
                + " casino_score INTEGER NOT NULL"
                + " );";

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createAdminTable() {
        // SQLite connection string

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_ADMIN + "("
                + "	username text NOT NULL UNIQUE,\n"
                + "	is_dev BIT NOT NULL"
                + ");";
        try (Connection conn = DriverManager.getConnection(DB_NAME);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_NAME);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // com.bit.telebot.game methods
    private void createGameEntry(String username) {
        String sql = "INSERT INTO " + TABLE_GAME +
                "(username,type_score,scramble_score,taboo_score,guess_score,casino_score) VALUES(?,0,0,0,0,0)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
    }

    private void incrementScore(String username, String game_name, int val) {
        // try to update the type score for the username, if not successful it means that
        // that user is not in db, so add em with a 1 score for type
        createGameEntry(username);
        String sql = "UPDATE " + TABLE_GAME +
                " SET " + game_name + " = " + game_name + " + " + val + " " +
                " WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void incrementTypeScore(String username) {
        incrementScore(username, "type_score", 1);
    }

    public void incrementScrambleScore(String username) {
        incrementScore(username, "scramble_score", 1);
    }

    public void incrementTabooScore(String username) {
        incrementScore(username, "taboo_score", 1);
    }

    public void incrementGuessScore(String username) {
        incrementScore(username, "guess_score", 1);
    }

    public void addToCasino(String username, int points) {
        incrementScore(username, "casino_score", points);
    }

    public void transferCasino(String from, String to, int points){
        addToCasino(from, -points);
        addToCasino(to, points);
    }

    private long getScore(String username, String game_name) {
        createGameEntry(username);
        String sql = "SELECT " + game_name + " "
                + "FROM " + TABLE_GAME + " WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                return rs.getInt(game_name);
            }
            return -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -2;
        }
    }

    public long getTypeScore(String username) {
        return getScore(username, "type_score");
    }

    public long getScrambleScore(String username) {
        return getScore(username, "scramble_score");
    }

    public long getTabooScore(String username) {
        return getScore(username, "taboo_score");
    }

    public long getGuessScore(String username) {
        return getScore(username, "guess_score");
    }

    public long getCasinoScore(String username) {
        return getScore(username, "casino_score");
    }

    // admin methods
    public boolean isAdmin(String username) {
        String sql = "SELECT username "
                + "FROM " + TABLE_ADMIN + " WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isDev(String username) {
        String sql = "SELECT is_dev "
                + "FROM " + TABLE_ADMIN + " WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("is_dev");
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addAdmin(String username) {
        String sql = "INSERT INTO " + TABLE_ADMIN + "(username,is_dev) VALUES(?, 0)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
    }

    public void addDev(String username) {
        addAdmin(username);

        String sql = "UPDATE " + TABLE_ADMIN +
                " SET is_dev = 1" +
                " WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}