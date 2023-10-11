package DTO;

public class Chuot_DTO {
    private int id;
    private String ten;
    private int soluong;
    private String chatlieu;
    private String mausac;

    public Chuot_DTO(int id, String ten, int soluong, String chatlieu, String mausac) {
        this.id = id;
        this.ten = ten;
        this.soluong = soluong;
        this.chatlieu = chatlieu;
        this.mausac = mausac;
    }

    public Chuot_DTO() {
    }

    public Chuot_DTO(String ten, int soluong, String chatlieu, String mausac) {
        this.ten = ten;
        this.soluong = soluong;
        this.chatlieu = chatlieu;
        this.mausac = mausac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }
}
