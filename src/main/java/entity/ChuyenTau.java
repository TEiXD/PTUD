package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChuyenTau {
    private String MaChuyenTau, GaDi, GaDen, MaTau, MaNhaGa;
    private LocalDateTime GioDi, GioDen;

    public ChuyenTau(String MaChuyenTau, String GaDi, String GaDen, LocalDateTime GioDi, LocalDateTime GioDen, String MaTau, String MaNhaGa) {
        this.MaChuyenTau = MaChuyenTau;
        this.GaDi = GaDi;
        this.GaDen = GaDen;
        this.GioDi = GioDi;
        this.GioDen = GioDen;
        this.MaTau = MaTau;
        this.MaNhaGa = MaNhaGa;
    }

    public String getMaChuyenTau() {
        return MaChuyenTau;
    }

    public void setMaChuyenTau(String maChuyenTau) {
        this.MaChuyenTau = maChuyenTau;
    }

    public String getGaDi() {
        return GaDi;
    }

    public void setGaDi(String gaDi) {
        this.GaDi = gaDi;
    }

    public String getGaDen() {
        return GaDen;
    }

    public void setGaDen(String gaDen) {
        this.GaDen = gaDen;
    }

    public LocalDateTime getGioDi() {
        return GioDi;
    }

    public void setGioDi(LocalDateTime gioDi) {
        this.GioDi = gioDi;
    }

    public LocalDateTime getGioDen() {
        return GioDen;
    }

    public void setGioDen(LocalDateTime gioDen) {
        this.GioDen = gioDen;
    }

    public String getMaTau() {
        return MaTau;
    }

    public void setMaTau(String maTau) {
        this.MaTau = maTau;
    }

    public String getMaNhaGa() {
        return MaNhaGa;
    }

    public void setMaNhaGa(String maNhaGa) {
        this.MaNhaGa = maNhaGa;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChuyenTau other = (ChuyenTau) obj;
        return Objects.equals(MaChuyenTau, other.MaChuyenTau);
    }

    @Override
    public String toString() {
        return "ChuyenTau [MaChuyenTau=" + MaChuyenTau + ", GaDi=" + GaDi + ", GaDen=" + GaDen + ", GioDi=" + GioDi + ", GioDen=" + GioDen + ", MaTau=" + MaTau + ", MaNhaGa=" + MaNhaGa + "]";
    }
}
