import java.sql.*;

class Database {
    // TODO: add taboo game
    private static Database db = null;

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    private final String DB_NAME = "jdbc:sqlite:database.db";
    private final String GAME_TABLE = "game";
    private final String ADMIN_TABLE = "admin";

    private Database() {
        createDatabase();
        createGameTable();
        createAdminTable();
    }

    private void createDatabase() {

        try {
            DriverManager.getConnection(DB_NAME);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createGameTable() {
        // create game table
        String sql = "CREATE TABLE IF NOT EXISTS " + GAME_TABLE + " (\n"
                + "	username text NOT NULL UNIQUE,\n"
                + "	type_score integer NOT NULL,\n"
                + " taboo integer NOT NULL,\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createAdminTable(){
        // create admin table
        String sql = "CREATE TABLE IF NOT EXISTS " + ADMIN_TABLE + " (\n"
                + "	username text NOT NULL UNIQUE);";

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

    // game methods
    private void createGameEntry(String username){
        // create a user if not exist in db
        String sql = "INSERT OR IGNORE INTO "+GAME_TABLE+
                "(username, type_score) VALUES (?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, 0);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void incrementTypeScore(String username) {
        // try to update the type score for the username, if not successful it means that
        // that user is not in db, so add em with a 1 score for type
        createGameEntry(username);

        String sql = "UPDATE "+GAME_TABLE+
                " SET type_score = type_score + 1" +
                " WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTypeScore(String username){
        String sql = "SELECT type_score "
                + "FROM "+GAME_TABLE+" WHERE username = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            // set the value
            pstmt.setString(1, username);
            ResultSet rs  = pstmt.executeQuery();
            // loop through the result set
            if (rs.next()) {
                return rs.getInt("type_score");
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}