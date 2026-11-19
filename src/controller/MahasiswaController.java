package controller;

import config.DatabaseConnection;
import dao.MahasiswaDAO;
import dao.MahasiswaDAOImpl;
import java.util.List;
import model.Mahasiswa;

public class MahasiswaController {
    private final MahasiswaDAO dao;

    public MahasiswaController() {
        this.dao = new MahasiswaDAOImpl(DatabaseConnection.getConnection());
    }

    public List<Mahasiswa> getAll() {
        return dao.getAll();
    }

    public String tambah(String nim, String nama, String prodi) {
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()) {
            return "Semua kolom wajib diisi.";
        }
        if (dao.getByNim(nim) != null) {
            return "NIM sudah terdaftar.";
        }
        dao.insert(new Mahasiswa(nim, nama, prodi));
        return "OK";
    }

    public String ubah(String nim, String nama, String prodi) {
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()) {
            return "Semua kolom wajib diisi.";
        }
        if (dao.getByNim(nim) == null) {
            return "Data tidak ditemukan. Pilih baris di tabel terlebih dahulu.";
        }
        dao.update(new Mahasiswa(nim, nama, prodi));
        return "OK";
    }

    public String hapus(String nim) {
        if (nim.isEmpty() || dao.getByNim(nim) == null) {
            return "Pilih baris di tabel terlebih dahulu.";
        }
        dao.delete(nim);
        return "OK";
    }
}