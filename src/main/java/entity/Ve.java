package entity;

import java.time.LocalDateTime;

public class Ve {
    private String maVe, tenVe, loaiVe;
    private LocalDateTime ngayDi, ngayVe;
    private KhachHang maKH;
    private NhanVien maNV;
    private ChuyenTau maCT;

    public Ve(String maVe, String tenVe, String loaiVe, LocalDateTime ngayDi, LocalDateTime ngayVe, KhachHang maKH,
			NhanVien maNV, ChuyenTau maCT) {
		super();
		this.maVe = maVe;
		this.tenVe = tenVe;
		this.loaiVe = loaiVe;
		this.ngayDi = ngayDi;
		this.ngayVe = ngayVe;
		this.maKH = maKH;
		this.maNV = maNV;
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

	public KhachHang getMaKH() {
		return maKH;
	}

	public void setMaKH(KhachHang maKH) {
		this.maKH = maKH;
	}

	public NhanVien getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
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
				+ ngayVe + ", maKH=" + maKH + ", maNV=" + maNV + ", maCT=" + maCT + "]";
	}
	
}
