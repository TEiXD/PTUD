package entity;

public class NhaGa {
    private String MaNhaGa, TenNhaGa, DiaChi;
    
    public NhaGa(String maNhaGa, String tenNhaGa, String diaChi) {
		super();
		MaNhaGa = maNhaGa;
		TenNhaGa = tenNhaGa;
		DiaChi = diaChi;
	}

	public NhaGa() {}
    
    public NhaGa(String maNhaGa) {
		this.MaNhaGa=maNhaGa;
	}


    
    public String getMaNhaGa() {
		return MaNhaGa;
	}

	public void setMaNhaGa(String maNhaGa) {
		MaNhaGa = maNhaGa;
	}

	public String getTenNhaGa() {
		return TenNhaGa;
	}

	public void setTenNhaGa(String tenNhaGa) {
		TenNhaGa = tenNhaGa;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((MaNhaGa == null) ? 0 : MaNhaGa.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NhaGa other = (NhaGa) obj;
        if (MaNhaGa == null) {
            if (other.MaNhaGa != null)
                return false;
        } else if (!MaNhaGa.equals(other.MaNhaGa))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "NhaGa [MaNhaGa=" + MaNhaGa + ", TenNhaGa=" + TenNhaGa + ", DiaChi=" + DiaChi + "]";
	}

	
}
