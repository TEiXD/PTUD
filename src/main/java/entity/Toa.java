package entity;

public class Toa {
	private Tau tau;
    private String loaiTau, maGhe;
    private int soToaTau, soPhong, soGhe;
	
    public Toa(Tau tau, String loaiTau, String maGhe, int soToaTau, int soPhong, int soGhe) {
		super();
		this.tau = tau;
		this.loaiTau = loaiTau;
		this.maGhe = maGhe;
		this.soToaTau = soToaTau;
		this.soPhong = soPhong;
		this.soGhe = soGhe;
	}
	public Tau getTau() {
		return tau;
	}
	public void setTau(Tau tau) {
		this.tau = tau;
	}
	public String getLoaiTau() {
		return loaiTau;
	}
	public void setLoaiTau(String loaiTau) {
		this.loaiTau = loaiTau;
	}
	public String getMaGhe() {
		return maGhe;
	}
	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}
	public int getSoToaTau() {
		return soToaTau;
	}
	public void setSoToaTau(int soToaTau) {
		this.soToaTau = soToaTau;
	}
	public int getSoPhong() {
		return soPhong;
	}
	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}
	public int getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}
	@Override
	public String toString() {
		return "Toa [tau=" + tau + ", loaiTau=" + loaiTau + ", maGhe=" + maGhe + ", soToaTau=" + soToaTau + ", soPhong="
				+ soPhong + ", soGhe=" + soGhe + "]";
	}
    
    
}
