package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String MaNV, HoTen, CCCD, GioiTinh, SDT, Email, TrinhDo, MaNhaGa;
    private LocalDate NgaySinh;

    public NhanVien(String MaNV, String HoTen, String CCCD, String GioiTinh, String SDT, String Email, LocalDate NgaySinh, String TrinhDo, String MaNhaGa) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.CCCD = CCCD;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.Email = Email;
        this.NgaySinh = NgaySinh;
        this.TrinhDo = TrinhDo;
        this.MaNhaGa = MaNhaGa;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        this.MaNV = maNV;
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

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.NgaySinh = ngaySinh;
    }

    public String getTrinhDo() {
        return TrinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.TrinhDo = trinhDo;
    }

    public String getMaNhaGa() {
        return MaNhaGa;
    }

    public void setMaNhaGa(String maNhaGa) {
        this.MaNhaGa = maNhaGa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaNV);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NhanVien other = (NhanVien) obj;
        return Objects.equals(MaNV, other.MaNV);
    }

    @Override
    public String toString() {
        return "NhanVien [MaNV=" + MaNV + ", HoTen=" + HoTen + ", CCCD=" + CCCD + ", GioiTinh=" + GioiTinh + ", SDT=" + SDT + ", Email=" + Email + ", NgaySinh=" + NgaySinh + ", TrinhDo=" + TrinhDo + ", MaNhaGa=" + MaNhaGa + "]";
    }
}
