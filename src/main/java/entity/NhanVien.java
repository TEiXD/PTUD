package entity;


import java.util.Objects;

public class NhanVien {
    private String maNV, hoTen, CCCD, GioiTinh, SDT, email;
    private String ngaySinh;
    private String trinhDo;
    private NhaGa nhaGa;

    
    public NhanVien(String maNV, String hoTen, String cCCD, String gioiTinh, String sDT, String email, String ngaySinh,
			String trinhDo, NhaGa ng) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		CCCD = cCCD;
		GioiTinh = gioiTinh;
		SDT = sDT;
		this.email = email;
		this.ngaySinh = ngaySinh;
		this.trinhDo = trinhDo;
		this.nhaGa = ng;
	}
    
    public NhanVien(String maNV) {
    	this.maNV = maNV;
    }
    

	public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getHoTen() {
		return hoTen;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public String getCCCD() {
		return CCCD;
	}


	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}


	public String getGioiTinh() {
		return GioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}


	public String getSDT() {
		return SDT;
	}


	public void setSDT(String sDT) {
		SDT = sDT;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}


	public String getTrinhDo() {
		return trinhDo;
	}


	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}


	public NhaGa getNhaGa() {
		return nhaGa;
	}


	public void setNhaGa(NhaGa nhaGa) {
		this.nhaGa = nhaGa;
	}


	@Override
    public int hashCode() {
        return Objects.hash(maNV);
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
        return Objects.equals(maNV, other.maNV);
    }


	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", CCCD=" + CCCD + ", GioiTinh=" + GioiTinh + ", SDT="
				+ SDT + ", email=" + email + ", ngaySinh=" + ngaySinh + ", trinhDo=" + trinhDo + ", nhaGa=" + nhaGa
				+ "]";
	}



}
