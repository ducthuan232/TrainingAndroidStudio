package com.example.trainningandroidstudio;

public class DoVat {
    private String ten, mota;
    private int Id;
    private byte[] hinh;

    public DoVat(int id, String name, String mota,  byte[] hinh) {
        this.ten = name;
        this.mota = mota;
        Id = id;
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public String getMota() {
        return mota;
    }

    public int getId() {
        return Id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public byte[] getHinh() {
        return hinh;
    }
}
