package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:mahasiswa.db";
    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
                initTable(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi database gagal: " + e.getMessage());
        }
        return connection;
    }

    private static void initTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS mahasiswa ("
                   + "nim TEXT PRIMARY KEY, "
                   + "nama TEXT NOT NULL, "
                   + "program_studi TEXT NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Gagal membuat tabel: " + e.getMessage());
        }
    }
}