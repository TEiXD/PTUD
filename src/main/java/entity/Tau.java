package entity;

import java.util.Objects;

public class Tau {
    private String maTau;
    private NhaGa nhaGa;
    private String loaiTau;

    public Tau(String maTau, NhaGa nhaGa, String loaiTau) {
		super();
		this.maTau = maTau;
		this.nhaGa = nhaGa;
		this.loaiTau = loaiTau;
	}

	public Tau(String maTau) {
		this.maTau = maTau;
	}
    
	public String getMaTau() {
		return maTau;
	}

	public void setMaTau(String maTau) {
		this.maTau = maTau;
	}

	public NhaGa getNhaGa() {
		return nhaGa;
	}

	public void setNhaGa(NhaGa nhaGa) {
		this.nhaGa = nhaGa;
	}

	public String getLoaiTau() {
		return loaiTau;
	}

	public void setLoaiTau(String loaiTau) {
		this.loaiTau = loaiTau;
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
        return Objects.equals(maTau, other.maTau);
    }

	@Override
	public String toString() {
		return "Tau [maTau=" + maTau + ", nhaGa=" + nhaGa + ", loaiTau=" + loaiTau + "]";
	}

	
}
