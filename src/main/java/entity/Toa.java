package entity;

import java.util.Objects;

public class Toa {
	private String MaToa;
	private Tau tau;
    private String TenToa;
    private int TinhTrang;
	private int soPhong;
	private String MaGhe;
	private int soGhe;
	
	public Toa() {}
	
	public Toa(String maToa) {
		this.MaToa = maToa;
	}
	
	public Toa(String maToa, Tau tau, String tenToa, int tinhTrang, int soPhong, String maGhe, int soGhe) {
		super();
		MaToa = maToa;
		this.tau = tau;
		TenToa = tenToa;
		TinhTrang = tinhTrang;
		this.soPhong = soPhong;
		MaGhe = maGhe;
		this.soGhe = soGhe;
	}

	public String getMaToa() {
		return MaToa;
	}

	public void setMaToa(String maToa) {
		MaToa = maToa;
	}

	public Tau getTau() {
		return tau;
	}

	public void setTau(Tau tau) {
		this.tau = tau;
	}

	public String getTenToa() {
		return TenToa;
	}

	public void setTenToa(String tenToa) {
		TenToa = tenToa;
	}

	public int getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		TinhTrang = tinhTrang;
	}

	public int getSoPhong() {
		return soPhong;
	}

	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}

	public String getMaGhe() {
		return MaGhe;
	}

	public void setMaGhe(String maGhe) {
		MaGhe = maGhe;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(MaToa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Toa other = (Toa) obj;
		return Objects.equals(MaToa, other.MaToa);
	}

	@Override
	public String toString() {
		return "Toa [MaToa=" + MaToa + ", tau=" + tau + ", TenToa=" + TenToa + ", TinhTrang=" + TinhTrang + ", soPhong="
				+ soPhong + ", MaGhe=" + MaGhe + ", soGhe=" + soGhe + "]";
	}
	
    
}
