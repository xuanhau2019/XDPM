package se.iuh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class NhanVien {
	@Id
	private String maNV;
	private String diaChi;
	private String soDT;
	private String tenNV;
	private String matKhau;
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
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
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public NhanVien(String maNV, String diaChi, String soDT, String tenNV, String matKhau) {
		super();
		this.maNV = maNV;
		this.diaChi = diaChi;
		this.soDT = soDT;
		this.tenNV = tenNV;
		this.matKhau = matKhau;
	}
	public NhanVien() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", diaChi=" + diaChi + ", soDT=" + soDT + ", tenNV=" + tenNV + ", matKhau="
				+ matKhau + "]";
	}
}
