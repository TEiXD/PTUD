package entity;


import java.util.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Objects;

public class ChuyenTau {
    private String maChuyenTau;
    private Tau tau;
    private NhaGa gaDi;
    private NhaGa gaDen;
    private Date ngayDi;
    private Time gioDi;
    private Date ngayDen;
    private Time gioDen;
    
    public ChuyenTau(String maChuyenTau, Tau tau, NhaGa gaDi, NhaGa gaDen, Date ngayDi, Time gioDi, Date ngayDen,
			Time gioDen) {
		super();
		this.maChuyenTau = maChuyenTau;
		this.tau = tau;
		this.gaDi = gaDi;
		this.gaDen = gaDen;
		this.ngayDi = ngayDi;
		this.gioDi = gioDi;
		this.ngayDen = ngayDen;
		this.gioDen = gioDen;
	}

	public ChuyenTau() {
		// TODO Auto-generated constructor stub
	}

    public ChuyenTau(String maCT) {
        this.maChuyenTau = maCT;
    }
    
    
	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	public void setMaChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}

	public Tau getTau() {
		return tau;
	}

	public void setTau(Tau tau) {
		this.tau = tau;
	}

	public NhaGa getGaDi() {
		return gaDi;
	}

	public void setGaDi(NhaGa gaDi) {
		this.gaDi = gaDi;
	}

	public NhaGa getGaDen() {
		return gaDen;
	}

	public void setGaDen(NhaGa gaDen) {
		this.gaDen = gaDen;
	}

	public Date getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}

	public Time getGioDi() {
		return gioDi;
	}

	public void setGioDi(Time gioDi) {
		this.gioDi = gioDi;
	}

	public Date getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}

	public Time getGioDen() {
		return gioDen;
	}

	public void setGioDen(Time gioDen) {
		this.gioDen = gioDen;
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
		return "ChuyenTau [maChuyenTau=" + maChuyenTau + ", tau=" + tau + ", gaDi=" + gaDi + ", gaDen=" + gaDen
				+ ", ngayDi=" + ngayDi + ", gioDi=" + gioDi + ", ngayDen=" + ngayDen + ", gioDen=" + gioDen + "]";
	}

}
