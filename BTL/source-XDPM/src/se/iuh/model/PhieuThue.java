package se.iuh.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PhieuThue")
public class PhieuThue {
	@Id
	private String maPhieuThue;
	@ManyToOne
	@JoinColumn(name = "khachHang")
	private KhachHang khachHang;
	@ManyToOne
	@JoinColumn(name = "nhanVien")
	private NhanVien nhanVien;
	private Date ngayThue;
	@OneToMany(mappedBy = "phieuThue", cascade = CascadeType.ALL)
	private List<ChiTietPhieuThue> listCTPT;
	public String getMaPhieuThue() {
		return maPhieuThue;
	}
	public void setMaPhieuThue(String maPhieuThue) {
		this.maPhieuThue = maPhieuThue;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public Date getNgayThue() {
		return ngayThue;
	}
	public void setNgayThue(Date ngayThue) {
		this.ngayThue = ngayThue;
	}
	public List<ChiTietPhieuThue> getListCTPT() {
		return listCTPT;
	}
	public void setListCTPT(List<ChiTietPhieuThue> listCTPT) {
		this.listCTPT = listCTPT;
	}
	public PhieuThue() {
		super();
	}
	public PhieuThue(String maPhieuThue, KhachHang khachHang, NhanVien nhanVien, Date ngayThue,
			List<ChiTietPhieuThue> listCTPT) {
		super();
		this.maPhieuThue = maPhieuThue;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayThue = ngayThue;
		this.listCTPT = listCTPT;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhieuThue == null) ? 0 : maPhieuThue.hashCode());
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
		PhieuThue other = (PhieuThue) obj;
		if (maPhieuThue == null) {
			if (other.maPhieuThue != null)
				return false;
		} else if (!maPhieuThue.equals(other.maPhieuThue))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhieuThue [maPhieuThue=" + maPhieuThue + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", ngayThue=" + ngayThue + ", listCTPT=" + listCTPT + "]";
	}
}
