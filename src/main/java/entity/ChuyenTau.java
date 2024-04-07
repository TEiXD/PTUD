package entity;

import java.sql.Date;
import java.time.LocalDateTime;

public class ChuyenTau {
    private String MaChuyenTau;
    private Tau MaTau;
    private String GaDi, GaDen;
    private String GioDi, GioDen;
    
    public ChuyenTau(String maCT) {
		// TODO Auto-generated constructor stub
    	this.MaChuyenTau=maCT;
	}

	public ChuyenTau(String maChuyenTau, Tau MaTau, String gaDi, String gaDen, String gioDi,
			String gioDen) {
		super();
		MaChuyenTau = maChuyenTau;
		this.MaTau = MaTau;
		GaDi = gaDi;
		GaDen = gaDen;
		GioDi = gioDi;
		GioDen = gioDen;
	}

	public String getMaChuyenTau() {
		return MaChuyenTau;
	}

	public void setMaChuyenTau(String maChuyenTau) {
		MaChuyenTau = maChuyenTau;
	}

	public Tau getMaTau() {
		return MaTau;
	}

	public void setMaTau(Tau MaTau) {
		this.MaTau = MaTau;
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

	public String getGioDi() {
		return GioDi;
	}

	public void setGioDi(String gioDi) {
		GioDi = gioDi;
	}

	public String getGioDen() {
		return GioDen;
	}

	public void setGioDen(String gioDen) {
		GioDen = gioDen;
	}

	@Override
	public String toString() {
		return "ChuyenTau [MaChuyenTau=" + MaChuyenTau + ", MaTau=" + MaTau + ", GaDi=" + GaDi + ", GaDen=" + GaDen
				+ ", GioDi=" + GioDi + ", GioDen=" + GioDen + "]";
	}
    
	
    
}
