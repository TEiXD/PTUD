package entity;

import java.util.Objects;

public class Tau {
    private String MaTau, LoaiTau, MaNhaGa;

    public Tau(String MaTau, String LoaiTau, String MaNhaGa) {
        this.MaTau = MaTau;
        this.LoaiTau = LoaiTau;
        this.MaNhaGa = MaNhaGa;
    }

    public String getMaTau() {
        return MaTau;
    }

    public void setMaTau(String maTau) {
        this.MaTau = maTau;
    }

    public String getLoaiTau() {
        return LoaiTau;
    }

    public void setLoaiTau(String loaiTau) {
        this.LoaiTau = loaiTau;
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
        Tau other = (Tau) obj;
        return Objects.equals(MaTau, other.MaTau);
    }

    @Override
    public String toString() {
        return "Tau [MaTau=" + MaTau + ", LoaiTau=" + LoaiTau + ", MaNhaGa=" + MaNhaGa + "]";
    }
}
