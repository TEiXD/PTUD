package entity;

import java.util.Objects;

public class KhachHang {
    private String MaKH, HoTen, CCCD, GioiTinh, SDT, Email;

    public KhachHang(String MaKH, String HoTen, String CCCD, String GioiTinh, String SDT, String Email) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.CCCD = CCCD;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.Email = Email;
    }
    public KhachHang(String maKH) {
    	this.MaKH=maKH;
    }
    
    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        this.MaKH = maKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        this.HoTen = hoTen;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.GioiTinh = gioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaKH);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KhachHang other = (KhachHang) obj;
        return Objects.equals(MaKH, other.MaKH);
    }

    @Override
    public String toString() {
        return "KhachHang [MaKH=" + MaKH + ", HoTen=" + HoTen + ", CCCD=" + CCCD + ", GioiTinh=" + GioiTinh + ", SDT=" + SDT + ", Email=" + Email + "]";
    }
}
