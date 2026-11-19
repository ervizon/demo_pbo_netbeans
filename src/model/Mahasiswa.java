package model;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String programStudi;

    public Mahasiswa(String nim, String nama, String programStudi) {
        this.nim = nim;
        this.nama = nama;
        this.programStudi = programStudi;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getProgramStudi() { return programStudi; }
}