package se.iuh.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TuaDia")
public class TuaDia {
	@Id
	private String maTua;
	private String tenTua;
	private String moTa;
	private int loaiTua;
	private int soNgayThue;
	private double phiTreHan;
	private double giaThue;
	@OneToMany(mappedBy = "tuaDia")
	private List<Dia> listDia;
	public String getMaTua() {
		return maTua;
	}
	public void setMaTua(String maTua) {
		this.maTua = maTua;
	}
	public String getTenTua() {
		return tenTua;
	}
	public void setTenTua(String tenTua) {
		this.tenTua = tenTua;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public int getLoaiTua() {
		return loaiTua;
	}
	public void setLoaiTua(int loaiTua) {
		this.loaiTua = loaiTua;
	}
	public int getSoNgayThue() {
		return soNgayThue;
	}
	public void setSoNgayThue(int soNgayThue) {
		this.soNgayThue = soNgayThue;
	}
	public double getPhiTreHan() {
		return phiTreHan;
	}
	public void setPhiTreHan(double phiTreHan) {
		this.phiTreHan = phiTreHan;
	}
	public List<Dia> getListDia() {
		return listDia;
	}
	public void setListDia(List<Dia> listDia) {
		this.listDia = listDia;
	}
	public double getGiaThue() {
		return giaThue;
	}
	public void setGiaThue(double giaThue) {
		this.giaThue = giaThue;
	}
	public TuaDia(String maTua, String tenTua, String moTa, int loaiTua, int soNgayThue, double phiTreHan, double giaThue) {
		super();
		this.maTua = maTua;
		this.tenTua = tenTua;
		this.moTa = moTa;
		this.loaiTua = loaiTua;
		this.soNgayThue = soNgayThue;
		this.phiTreHan = phiTreHan;
		this.giaThue = giaThue;
	}
	
	public TuaDia(String maTua, String tenTua, String moTa, int loaiTua, int soNgayThue, double phiTreHan, double giaThue,
			List<Dia> listDia) {
		super();
		this.maTua = maTua;
		this.tenTua = tenTua;
		this.moTa = moTa;
		this.loaiTua = loaiTua;
		this.soNgayThue = soNgayThue;
		this.phiTreHan = phiTreHan;
		this.giaThue = giaThue;
		this.listDia = listDia;
	}
	public TuaDia() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maTua == null) ? 0 : maTua.hashCode());
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
		TuaDia other = (TuaDia) obj;
		if (maTua == null) {
			if (other.maTua != null)
				return false;
		} else if (!maTua.equals(other.maTua))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TuaDia [maTua=" + maTua + ", tenTua=" + tenTua + ", moTa=" + moTa + ", loaiTua=" + loaiTua
				+ ", soNgayThue=" + soNgayThue + ", phiTreHan=" + phiTreHan + ", giaThue=" + giaThue + "]";
	}
}
