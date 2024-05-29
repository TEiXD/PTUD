package entity;

import java.util.Objects;

public class Ghe {
    private String maSlot;
    private int soSlot;
    private Toa toa;
    private int tinhTrang;

    public Ghe() {
    }

    public Ghe(String maSlot, int soSlot) {
        this.maSlot = maSlot;
        this.soSlot = soSlot;
    }

    public Ghe(String maSlot, int soSlot, Toa toa, int tinhTrang) {
		super();
		this.maSlot = maSlot;
		this.soSlot = soSlot;
		this.toa = toa;
		this.tinhTrang = tinhTrang;
	}


    public String getMaSlot() {
		return maSlot;
	}

	public void setMaSlot(String maSlot) {
		this.maSlot = maSlot;
	}

	public int getSoSlot() {
		return soSlot;
	}

	public void setSoSlot(int soSlot) {
		this.soSlot = soSlot;
	}

	public Toa getToa() {
		return toa;
	}

	public void setToa(Toa toa) {
		this.toa = toa;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}


    @Override
	public int hashCode() {
		return Objects.hash(maSlot, soSlot, tinhTrang, toa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ghe other = (Ghe) obj;
		return Objects.equals(maSlot, other.maSlot) && soSlot == other.soSlot && tinhTrang == other.tinhTrang
				&& Objects.equals(toa, other.toa);
	}

	@Override
	public String toString() {
		return "Slot [maSlot=" + maSlot + ", soSlot=" + soSlot + ", toa=" + toa + ", tinhTrang=" + tinhTrang + "]";
	}
}