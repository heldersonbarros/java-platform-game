package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
    private static Connection connection;

    public static synchronized Connection getConnection(){
        String url = "JDBC:sqlite:test.db";

        try {
            if (connection == null){
                connection = DriverManager.getConnection(url);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTables(){
        Connection conn = getConnection();
        String player_sql = """
                CREATE TABLE IF NOT EXISTS player (
                id integer PRIMARY KEY,
                name TEXT NOT NULL,
                collectables int DEFAULT 0,
                current_skin int DEFAULT 0
                );""";

        String skin_sql = """
                CREATE TABLE IF NOT EXISTS skin (
                id integer PRIMARY KEY,
                name TEXT NOT NULL
                );""";

        String player_skin = """
                CREATE TABLE IF NOT EXISTS player_skin (
                player_id INTEGER NOT NULL REFERENCES player(id),
                skin_id INTEGER NOT NULL REFERENCES player(id)
                );""";

        try {
            Statement statement = conn.createStatement();
            statement.execute(player_sql);
            statement.execute(skin_sql);
            statement.execute(player_skin);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
