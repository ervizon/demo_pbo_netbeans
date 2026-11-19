package dao;

import java.util.List;
import model.Mahasiswa;

public interface MahasiswaDAO {
    void insert(Mahasiswa mahasiswa);
    List<Mahasiswa> getAll();
    Mahasiswa getByNim(String nim);
    void update(Mahasiswa mahasiswa);
    void delete(String nim);
}