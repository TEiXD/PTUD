package entity;

public class NhaGa {
    private String MaNhaGa;
    private String TenNhaGa;
    private String DiaDiem;

    public NhaGa(String MaNhaGa, String TenNhaGa, String DiaDiem) {
        this.MaNhaGa = MaNhaGa;
        this.TenNhaGa = TenNhaGa;
        this.DiaDiem = DiaDiem;
    }

    public NhaGa(String maNhaGa) {
        this.MaNhaGa = maNhaGa;
    }

    public String getMaNhaGa() {
        return MaNhaGa;
    }

    public void setMaNhaGa(String maNhaGa) {
        this.MaNhaGa = maNhaGa;
    }

    public String getTenNhaGa() {
        return TenNhaGa;
    }

    public void setTenNhaGa(String tenNhaGa) {
        this.TenNhaGa = tenNhaGa;
    }

    public String getDiaDiem() {
        return DiaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.DiaDiem = diaDiem;
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
        return MaNhaGa.equals(other.MaNhaGa);
    }

    @Override
    public String toString() {
        return "NhaGa [MaNhaGa=" + MaNhaGa + ", TenNhaGa=" + TenNhaGa + ", DiaDiem=" + DiaDiem + "]";
    }
}