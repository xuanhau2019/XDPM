package se.iuh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KhachHang")
public class KhachHang {
	@Id
	private String maKH;
	private String diaChi;
	private String soDT;
	private String tenKH;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public KhachHang(String maKH, String diaChi, String soDT, String tenKH) {
		super();
		this.maKH = maKH;
		this.diaChi = diaChi;
		this.soDT = soDT;
		this.tenKH = tenKH;
	}
	public KhachHang() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", diaChi=" + diaChi + ", soDT=" + soDT + ", tenKH=" + tenKH + "]";
	}
}
