package entity;

import java.util.Objects;

public class Tau {
    private String MaTau;
    private NhaGa NhaGa;
    private String LoaiTau;
    private int SoLuongToa, SoLuongGhe;

	public Tau(String maTau, entity.NhaGa nhaGa, String loaiTau, int soLuongToa, int soLuongGhe) {
		super();
		MaTau = maTau;
		NhaGa = nhaGa;
		LoaiTau = loaiTau;
		SoLuongToa = soLuongToa;
		SoLuongGhe = soLuongGhe;
	}

	public Tau(String maTau) {
		this.MaTau = maTau;
	}
    
	
	public String getMaTau() {
		return MaTau;
	}

	public void setMaTau(String maTau) {
		MaTau = maTau;
	}

	public NhaGa getNhaGa() {
		return NhaGa;
	}

	public void setNhaGa(NhaGa nhaGa) {
		NhaGa = nhaGa;
	}

	public String getLoaiTau() {
		return LoaiTau;
	}

	public void setLoaiTau(String loaiTau) {
		LoaiTau = loaiTau;
	}

	public int getSoLuongToa() {
		return SoLuongToa;
	}

	public void setSoLuongToa(int soLuongToa) {
		SoLuongToa = soLuongToa;
	}

	public int getSoLuongGhe() {
		return SoLuongGhe;
	}

	public void setSoLuongGhe(int soLuongGhe) {
		SoLuongGhe = soLuongGhe;
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
		return "Tau [MaTau=" + MaTau + ", NhaGa=" + NhaGa + ", LoaiTau=" + LoaiTau + ", SoLuongToa=" + SoLuongToa
				+ ", SoLuongGhe=" + SoLuongGhe + "]";
	}

	
	
}
