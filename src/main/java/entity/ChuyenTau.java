package entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChuyenTau {
    private String MaChuyenTau;
    private Tau LTau;
    private String GaDi, GaDen;
    private String GioDi, GioDen;
    
    public ChuyenTau(String maCT) {
		// TODO Auto-generated constructor stub
    	this.MaChuyenTau=maCT;
	}

	public ChuyenTau(String maChuyenTau, Tau lTau, String gaDi, String gaDen, String gioDi,
			String gioDen) {
		super();
		MaChuyenTau = maChuyenTau;
		LTau = lTau;
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

	public Tau getLTau() {
		return LTau;
	}

	public void setLTau(Tau lTau) {
		LTau = lTau;
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
		return "ChuyenTau [MaChuyenTau=" + MaChuyenTau + ", LTau=" + LTau + ", GaDi=" + GaDi + ", GaDen=" + GaDen
				+ ", GioDi=" + GioDi + ", GioDen=" + GioDen + "]";
	}
    
	
    
}
