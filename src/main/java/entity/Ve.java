package entity;

import java.sql.Date;
import java.util.Objects;

public class Ve {
    private String MaVe;
    private String GaDi, GaDen;
    private Date NgayDi;
    private String GioDi;
    private Date NgayVe;
    private String GioVe;
    private String Toa;
    private String LoaiCho;
    private int choNgoi;
    private float giaVe;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    
    public Ve(String maVe, String tenVe, String loaiVe, String ngayDistr, String ngayVestr, KhachHang kh, NhanVien nv, ChuyenTau ct) {
		// TODO Auto-generated constructor stub
	}

	public Ve(String maVe, String gaDi, String gaDen, Date ngayDi, String gioDi, Date ngayVe, String gioVe, String toa,
			String loaiCho, int choNgoi, float giaVe, KhachHang khachHang, NhanVien nhanVien) {
		super();
		MaVe = maVe;
		GaDi = gaDi;
		GaDen = gaDen;
		NgayDi = ngayDi;
		GioDi = gioDi;
		NgayVe = ngayVe;
		GioVe = gioVe;
		Toa = toa;
		LoaiCho = loaiCho;
		this.choNgoi = choNgoi;
		this.giaVe = giaVe;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}

	public String getMaVe() {
		return MaVe;
	}

	public void setMaVe(String maVe) {
		MaVe = maVe;
	}

	public String getGaDi() {
		return GaDi;
	}

	public void setGaDi(String gaDi) {
		GaDi = gaDi;
	}

	public String getGaDen() {
		return GaDen;
	}

	public void setGaDen(String gaDen) {
		GaDen = gaDen;
	}

	public Date getNgayDi() {
		return NgayDi;
	}

	public void setNgayDi(Date ngayDi) {
		NgayDi = ngayDi;
	}

	public String getGioDi() {
		return GioDi;
	}

	public void setGioDi(String gioDi) {
		GioDi = gioDi;
	}

	public Date getNgayVe() {
		return NgayVe;
	}

	public void setNgayVe(Date ngayVe) {
		NgayVe = ngayVe;
	}

	public String getGioVe() {
		return GioVe;
	}

	public void setGioVe(String gioVe) {
		GioVe = gioVe;
	}

	public String getToa() {
		return Toa;
	}

	public void setToa(String toa) {
		Toa = toa;
	}

	public String getLoaiCho() {
		return LoaiCho;
	}

	public void setLoaiCho(String loaiCho) {
		LoaiCho = loaiCho;
	}

	public int getChoNgoi() {
		return choNgoi;
	}

	public void setChoNgoi(int choNgoi) {
		this.choNgoi = choNgoi;
	}

	public float getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(float giaVe) {
		this.giaVe = giaVe;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(MaVe);
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
		return Objects.equals(MaVe, other.MaVe);
	}

	@Override
	public String toString() {
		return "Ve [MaVe=" + MaVe + ", GaDi=" + GaDi + ", GaDen=" + GaDen + ", NgayDi=" + NgayDi + ", GioDi=" + GioDi
				+ ", NgayVe=" + NgayVe + ", GioVe=" + GioVe + ", Toa=" + Toa + ", LoaiCho=" + LoaiCho + ", choNgoi="
				+ choNgoi + ", giaVe=" + giaVe + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + "]";
	}
}
