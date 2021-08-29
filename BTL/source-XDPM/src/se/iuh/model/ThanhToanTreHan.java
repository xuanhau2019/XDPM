package se.iuh.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ThanhToanTreHan")
public class ThanhToanTreHan {
	@Id
	private String maThanhToan;
	private Date ngayThanhToan;
	@ManyToOne
	@JoinColumn(name = "nhanVien")
	private NhanVien nhanVien;
	@OneToOne
	@JoinColumn(name = "ttPhi")
	private PhiTreHan ttPhi;
	public String getMaThanhToan() {
		return maThanhToan;
	}
	public void setMaThanhToan(String maThanhToan) {
		this.maThanhToan = maThanhToan;
	}
	public Date getNgayThanhToan() {
		return ngayThanhToan;
	}
	public void setNgayThanhToan(Date ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public PhiTreHan getTtPhi() {
		return ttPhi;
	}
	public void setTtPhi(PhiTreHan ttPhi) {
		this.ttPhi = ttPhi;
	}
	public ThanhToanTreHan(String maThanhToan, Date ngayThanhToan, NhanVien nhanVien, PhiTreHan ttPhi) {
		super();
		this.maThanhToan = maThanhToan;
		this.ngayThanhToan = ngayThanhToan;
		this.nhanVien = nhanVien;
		this.ttPhi = ttPhi;
	}
	public ThanhToanTreHan() {
		super();
	}
	@Override
	public String toString() {
		return "ThanhToanTreHan [maThanhToan=" + maThanhToan + ", ngayThanhToan=" + ngayThanhToan + ", nhanVien="
				+ nhanVien + ", ttPhi=" + ttPhi + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maThanhToan == null) ? 0 : maThanhToan.hashCode());
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
		ThanhToanTreHan other = (ThanhToanTreHan) obj;
		if (maThanhToan == null) {
			if (other.maThanhToan != null)
				return false;
		} else if (!maThanhToan.equals(other.maThanhToan))
			return false;
		return true;
	}
}
