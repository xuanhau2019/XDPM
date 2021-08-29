package se.iuh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PhiTreHan")
public class PhiTreHan {
	@Id
	private String maPhiTreHan;
	@OneToOne
	@JoinColumn(name = "phieuTra")
	private PhieuTra phieuTra;
	private double phiTreHan;
	public String getMaPhiTreHan() {
		return maPhiTreHan;
	}
	public void setMaPhiTreHan(String maPhiTreHan) {
		this.maPhiTreHan = maPhiTreHan;
	}
	public PhieuTra getPhieuTra() {
		return phieuTra;
	}
	public void setPhieuTra(PhieuTra phieuTra) {
		this.phieuTra = phieuTra;
	}
	public double getPhiTreHan() {
		return phiTreHan;
	}
	public void setPhiTreHan(double phiTreHan) {
		this.phiTreHan = phiTreHan;
	}
	public PhiTreHan(String maPhiTreHan, PhieuTra phieuTra, double phiTreHan) {
		super();
		this.maPhiTreHan = maPhiTreHan;
		this.phieuTra = phieuTra;
		this.phiTreHan = phiTreHan;
	}
	public PhiTreHan() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhiTreHan == null) ? 0 : maPhiTreHan.hashCode());
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
		PhiTreHan other = (PhiTreHan) obj;
		if (maPhiTreHan == null) {
			if (other.maPhiTreHan != null)
				return false;
		} else if (!maPhiTreHan.equals(other.maPhiTreHan))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhiTreHan [maPhiTreHan=" + maPhiTreHan + ", phieuTra=" + phieuTra + ", phiTreHan=" + phiTreHan + "]";
	}
}
