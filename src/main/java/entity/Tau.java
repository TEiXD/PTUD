package entity;

import java.util.Objects;

public class Tau {
    private String MaTau;
    private String TenTau;
    private NhaGa NhaGa;
    private String LoaiTau;
	
	public Tau(String maTau, String tenTau, entity.NhaGa nhaGa, String loaiTau) {
		super();
		MaTau = maTau;
		TenTau = tenTau;
		NhaGa = nhaGa;
		LoaiTau = loaiTau;
	}

	public Tau() {}

	public Tau(String maTau) {
		this.MaTau = maTau;
	}

	public String getMaTau() {
		return MaTau;
	}

	public void setMaTau(String maTau) {
		MaTau = maTau;
	}

	public String getTenTau() {
		return TenTau;
	}

	public void setTenTau(String tenTau) {
		TenTau = tenTau;
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

	@Override
	public int hashCode() {
		return Objects.hash(MaTau);
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
		return "Tau [MaTau=" + MaTau + ", TenTau=" + TenTau + ", NhaGa=" + NhaGa + ", LoaiTau=" + LoaiTau + "]";
	}
}
