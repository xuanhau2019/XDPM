package se.iuh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Dia")
public class Dia {
	@Id
	private String maDia;
	@ManyToOne
	@JoinColumn(name = "tuaDia")
	private TuaDia tuaDia;
	private String trangThai;
	public String getMaDia() {
		return maDia;
	}
	public void setMaDia(String maDia) {
		this.maDia = maDia;
	}
	public TuaDia getTuaDia() {
		return tuaDia;
	}
	public void setTuaDia(TuaDia tuaDia) {
		this.tuaDia = tuaDia;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maDia == null) ? 0 : maDia.hashCode());
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
		Dia other = (Dia) obj;
		if (maDia == null) {
			if (other.maDia != null)
				return false;
		} else if (!maDia.equals(other.maDia))
			return false;
		return true;
	}
	public Dia(String maDia, TuaDia tuaDia, String trangThai) {
		super();
		this.maDia = maDia;
		this.tuaDia = tuaDia;
		this.trangThai = trangThai;
	}
	public Dia() {
		super();
	}
	@Override
	public String toString() {
		return "Dia [maDia=" + maDia + ", tuaDia=" + tuaDia + ", trangThai=" + trangThai + "]";
	}
}
