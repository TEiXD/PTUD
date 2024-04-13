package entity;

import java.util.Objects;

public class Tau {
    private String MaTau;
    private NhaGa NhaGa;
    private String LoaiTau;

    public Tau(String MaTau, NhaGa NhaGa, String LoaiTau) {
        this.MaTau = MaTau;
        this.NhaGa = NhaGa;
        this.LoaiTau = LoaiTau;
    }


	public Tau(String maTau) {
		this.MaTau = maTau;
	}
    
	public String getMaTau() {
		return MaTau;
	}

	public void setMaTau(String MaTau) {
		this.MaTau = MaTau;
	}

	public NhaGa getNhaGa() {
		return NhaGa;
	}

	public void setNhaGa(NhaGa NhaGa) {
		this.NhaGa = NhaGa;
	}

	public String getLoaiTau() {
		return LoaiTau;
	}

	public void setLoaiTau(String LoaiTau) {
		this.LoaiTau = LoaiTau;
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
		return "Tau [maTau=" + MaTau + ", nhaGa=" + NhaGa + ", loaiTau=" + LoaiTau + "]";
	}

	
}
