package se.iuh.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PhieuTra")
public class PhieuTra {
	@Id
	private String maPhieuTra;
	@ManyToOne
	@JoinColumn(name = "dia")
	private Dia dia;
	@ManyToOne
	@JoinColumn(name = "phieuThue")
	private PhieuThue phieuThue;
	@ManyToOne
	@JoinColumn(name = "nhanVien")
	private NhanVien nhanVien;
	private Date ngayTra;
	public String getMaPhieuTra() {
		return maPhieuTra;
	}
	public void setMaPhieuTra(String maPhieuTra) {
		this.maPhieuTra = maPhieuTra;
	}
	public Dia getDia() {
		return dia;
	}
	public void setDia(Dia dia) {
		this.dia = dia;
	}
	public PhieuThue getPhieuThue() {
		return phieuThue;
	}
	public void setPhieuThue(PhieuThue phieuThue) {
		this.phieuThue = phieuThue;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public Date getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	public PhieuTra(String maPhieuTra, Dia dia, PhieuThue phieuThue, NhanVien nhanVien, Date ngayTra) {
		super();
		this.maPhieuTra = maPhieuTra;
		this.dia = dia;
		this.phieuThue = phieuThue;
		this.nhanVien = nhanVien;
		this.ngayTra = ngayTra;
	}
	public PhieuTra() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhieuTra == null) ? 0 : maPhieuTra.hashCode());
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
		PhieuTra other = (PhieuTra) obj;
		if (maPhieuTra == null) {
			if (other.maPhieuTra != null)
				return false;
		} else if (!maPhieuTra.equals(other.maPhieuTra))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhieuTra [maPhieuTra=" + maPhieuTra + ", dia=" + dia + ", phieuThue=" + phieuThue + ", nhanVien="
				+ nhanVien + ", ngayTra=" + ngayTra + "]";
	}
}
