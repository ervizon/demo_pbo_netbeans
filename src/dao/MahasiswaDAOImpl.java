package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mahasiswa;

public class MahasiswaDAOImpl implements MahasiswaDAO {
    private final Connection connection;

    public MahasiswaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Mahasiswa mahasiswa) {
        String sql = "INSERT INTO mahasiswa (nim, nama, program_studi) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mahasiswa.getNim());
            ps.setString(2, mahasiswa.getNama());
            ps.setString(3, mahasiswa.getProgramStudi());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan data: " + e.getMessage());
        }
    }

    @Override
    public List<Mahasiswa> getAll() {
        List<Mahasiswa> daftar = new ArrayList<>();
        String sql = "SELECT nim, nama, program_studi FROM mahasiswa";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                daftar.add(new Mahasiswa(
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("program_studi")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data: " + e.getMessage());
        }
        return daftar;
    }

    @Override
    public Mahasiswa getByNim(String nim) {
        String sql = "SELECT nim, nama, program_studi FROM mahasiswa WHERE nim = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nim);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Mahasiswa(
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getString("program_studi")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal mencari data: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Mahasiswa mahasiswa) {
        String sql = "UPDATE mahasiswa SET nama = ?, program_studi = ? WHERE nim = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mahasiswa.getNama());
            ps.setString(2, mahasiswa.getProgramStudi());
            ps.setString(3, mahasiswa.getNim());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal memperbarui data: " + e.getMessage());
        }
    }

    @Override
    public void delete(String nim) {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nim);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}