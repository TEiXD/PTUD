package entity;


public class Ve {
    private String maVe, tenVe, loaiVe;
    private String ngayDi, ngayVe;
    private KhachHang maKH;
    private NhanVien nhanVien;
    private ChuyenTau maCT;

    public Ve(String maVe, String tenVe, String loaiVe, String ngayDistr, String ngayVestr, KhachHang maKH,
			NhanVien nhanVien, ChuyenTau maCT) {
		super();
		this.maVe = maVe;
		this.tenVe = tenVe;
		this.loaiVe = loaiVe;
		this.ngayDi = ngayDistr;
		this.ngayVe = ngayVestr;
		this.maKH = maKH;
		this.nhanVien = nhanVien;
		this.maCT = maCT;
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

	public String getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(String ngayDi) {
		this.ngayDi = ngayDi;
	}

	public String getNgayVe() {
		return ngayVe;
	}

	public void setNgayVe(String ngayVe) {
		this.ngayVe = ngayVe;
	}

	public KhachHang getMaKH() {
		return maKH;
	}

	public void setMaKH(KhachHang maKH) {
		this.maKH = maKH;
	}

	public NhanVien getnhanVien() {
		return nhanVien;
	}

	public void setnhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public ChuyenTau getMaCT() {
		return maCT;
	}

	public void setMaCT(ChuyenTau maCT) {
		this.maCT = maCT;
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
		return "Ve [maVe=" + maVe + ", tenVe=" + tenVe + ", loaiVe=" + loaiVe + ", ngayDi=" + ngayDi + ", ngayVe="
				+ ngayVe + ", maKH=" + maKH + ", nhanVien=" + nhanVien + ", maCT=" + maCT + "]";
	}
	
}
