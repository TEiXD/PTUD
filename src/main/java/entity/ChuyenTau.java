package entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChuyenTau {
    private String maChuyenTau;
    private Tau mTau;
    private NhaGa nhaGa;
    private String gaDi, gaDen;
    private LocalDateTime GioDi, GioDen;
    
    
    public ChuyenTau(String maChuyenTau, Tau mTau, NhaGa nhaGa, String gaDi, String gaDen, LocalDateTime gioDi,
			LocalDateTime gioDen) {
		this.maChuyenTau = maChuyenTau;
		this.mTau = mTau;
		this.nhaGa = nhaGa;
		this.gaDi = gaDi;
		this.gaDen = gaDen;
		GioDi = gioDi;
		GioDen = gioDen;
	}



	public ChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}



	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	public void setMaChuyenTau(String maChuyenTau) {
		maChuyenTau = maChuyenTau;
	}

	public Tau getmTau() {
		return mTau;
	}

	public void setmTau(Tau mTau) {
		this.mTau = mTau;
	}

	public NhaGa getNhaGa() {
		return nhaGa;
	}

	public void setNhaGa(NhaGa nhaGa) {
		this.nhaGa = nhaGa;
	}

	public String getGaDi() {
		return gaDi;
	}

	public void setGaDi(String gaDi) {
		this.gaDi = gaDi;
	}

	public String getGaDen() {
		return gaDen;
	}

	public void setGaDen(String gaDen) {
		this.gaDen = gaDen;
	}

	public LocalDateTime getGioDi() {
		return GioDi;
	}

	public void setGioDi(LocalDateTime gioDi) {
		GioDi = gioDi;
	}

	public LocalDateTime getGioDen() {
		return GioDen;
	}

	public void setGioDen(LocalDateTime gioDen) {
		GioDen = gioDen;
	}

	@Override
    public int hashCode() {
        return Objects.hash(maChuyenTau);
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChuyenTau other = (ChuyenTau) obj;
        return Objects.equals(maChuyenTau, other.maChuyenTau);
    }

	@Override
	public String toString() {
		return "ChuyenTau [maChuyenTau=" + maChuyenTau + ", mTau=" + mTau + ", nhaGa=" + nhaGa + ", gaDi=" + gaDi
				+ ", gaDen=" + gaDen + ", GioDi=" + GioDi + ", GioDen=" + GioDen + "]";
	}
	
    
}
