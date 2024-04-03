package entity;

import java.time.LocalDateTime;

public class Ve {
    private String maVe, tenVe, loaiVe, maKH, maNV, maChuyenTau;
    private LocalDateTime ngayDi, ngayVe;

    public Ve(String maVe, String tenVe, String loaiVe, LocalDateTime ngayDi, LocalDateTime ngayVe, String maKH, String maNV, String maChuyenTau) {
        this.maVe = maVe;
        this.tenVe = tenVe;
        this.loaiVe = loaiVe;
        this.ngayDi = ngayDi;
        this.ngayVe = ngayVe;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maChuyenTau = maChuyenTau;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getTenVe() {
        return tenVe;
    }

    public void setTenVe(String tenVe) {
        this.tenVe = tenVe;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(String loaiVe) {
        this.loaiVe = loaiVe;
    }

    public LocalDateTime getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(LocalDateTime ngayDi) {
        this.ngayDi = ngayDi;
    }

    public LocalDateTime getNgayVe() {
        return ngayVe;
    }

    public void setNgayVe(LocalDateTime ngayVe) {
        this.ngayVe = ngayVe;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaChuyenTau() {
        return maChuyenTau;
    }

    public void setMaChuyenTau(String maChuyenTau) {
        this.maChuyenTau = maChuyenTau;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ve other = (Ve) obj;
        return maVe.equals(other.maVe);
    }

    @Override
    public String toString() {
        return "Ve [maVe=" + maVe + ", tenVe=" + tenVe + ", loaiVe=" + loaiVe + ", ngayDi=" + ngayDi + ", ngayVe=" + ngayVe + ", maKH=" + maKH + ", maNV=" + maNV + ", maChuyenTau=" + maChuyenTau + "]";
    }
}
