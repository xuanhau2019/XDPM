package se.iuh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ChiTietPhieuThue")
public class ChiTietPhieuThue {
	@Id
	private String maCTPT;
	@ManyToOne
	@JoinColumn(name = "diaThue", referencedColumnName = "maDia")
	private Dia diaThue;
	@ManyToOne
	@JoinColumn(name = "phieuThue", referencedColumnName = "maPhieuThue")
	private PhieuThue phieuThue;
	private double giaThue;
	private double phiTreHan;
	private Date hanTraDia;
	public String getMaCTPT() {
		return maCTPT;
	}
	public void setMaCTPT(String maCTPT) {
		this.maCTPT = maCTPT;
	}
	public Dia getDiaThue() {
		return diaThue;
	}
	public void setDiaThue(Dia diaThue) {
		this.diaThue = diaThue;
	}
	public PhieuThue getPhieuThue() {
		return phieuThue;
	}
	public void setPhieuThue(PhieuThue phieuThue) {
		this.phieuThue = phieuThue;
	}
	public double getGiaThue() {
		return giaThue;
	}
	public void setGiaThue(double giaThue) {
		this.giaThue = giaThue;
	}
	public double getPhiTreHan() {
		return phiTreHan;
	}
	public void setPhiTreHan(double phiTreHan) {
		this.phiTreHan = phiTreHan;
	}
	public Date getHanTraDia() {
		return hanTraDia;
	}
	public void setHanTraDia(Date hanTraDia) {
		this.hanTraDia = hanTraDia;
	}
	public ChiTietPhieuThue(String maCTPT, Dia diaThue, PhieuThue phieuThue, double giaThue, double phiTreHan,
			Date hanTraDia) {
		super();
		this.maCTPT = maCTPT;
		this.diaThue = diaThue;
		this.phieuThue = phieuThue;
		this.giaThue = giaThue;
		this.phiTreHan = phiTreHan;
		this.hanTraDia = hanTraDia;
	}
	public ChiTietPhieuThue() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCTPT == null) ? 0 : maCTPT.hashCode());
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
		ChiTietPhieuThue other = (ChiTietPhieuThue) obj;
		if (maCTPT == null) {
			if (other.maCTPT != null)
				return false;
		} else if (!maCTPT.equals(other.maCTPT))
			return false;
		return true;
	}
	
}
