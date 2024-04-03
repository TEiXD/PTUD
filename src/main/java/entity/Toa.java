package entity;

public class Toa {
    private String MaTau, LoaiTau, MaGhe;
    private int SoToaTau, SoPhong, SoGhe;

    public Toa(String MaTau, int SoToaTau, String LoaiTau, int SoPhong, String MaGhe, int SoGhe) {
        this.MaTau = MaTau;
        this.SoToaTau = SoToaTau;
        this.LoaiTau = LoaiTau;
        this.SoPhong = SoPhong;
        this.MaGhe = MaGhe;
        this.SoGhe = SoGhe;
    }

    public String getMaTau() {
        return MaTau;
    }

    public void setMaTau(String maTau) {
        this.MaTau = maTau;
    }

    public int getSoToaTau() {
        return SoToaTau;
    }

    public void setSoToaTau(int soToaTau) {
        this.SoToaTau = soToaTau;
    }

    public String getLoaiTau() {
        return LoaiTau;
    }

    public void setLoaiTau(String loaiTau) {
        this.LoaiTau = loaiTau;
    }

    public int getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(int soPhong) {
        this.SoPhong = soPhong;
    }

    public String getMaGhe() {
        return MaGhe;
    }

    public void setMaGhe(String maGhe) {
        this.MaGhe = maGhe;
    }

    public int getSoGhe() {
        return SoGhe;
    }

    public void setSoGhe(int soGhe) {
        this.SoGhe = soGhe;
    }

    @Override
    public String toString() {
        return "Toa [MaTau=" + MaTau + ", SoToaTau=" + SoToaTau + ", LoaiTau=" + LoaiTau + ", SoPhong=" + SoPhong + ", MaGhe=" + MaGhe + ", SoGhe=" + SoGhe + "]";
    }
}
