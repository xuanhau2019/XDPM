package se.iuh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PK_Dia_PhieuThue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5308720675307735018L;
	@Column(name = "diaThue")
	private String diaThue;
	@Column(name = "phieuThue")
	private String phieuThue;
	public String getDiaThue() {
		return diaThue;
	}
	public void setDiaThue(String diaThue) {
		this.diaThue = diaThue;
	}
	public String getPhieuThue() {
		return phieuThue;
	}
	public void setPhieuThue(String phieuThue) {
		this.phieuThue = phieuThue;
	}
	public PK_Dia_PhieuThue(String diaThue, String phieuThue) {
		super();
		this.diaThue = diaThue;
		this.phieuThue = phieuThue;
	}
	public PK_Dia_PhieuThue() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PK_Dia_PhieuThue [diaThue=" + diaThue + ", phieuThue=" + phieuThue + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaThue == null) ? 0 : diaThue.hashCode());
		result = prime * result + ((phieuThue == null) ? 0 : phieuThue.hashCode());
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
		PK_Dia_PhieuThue other = (PK_Dia_PhieuThue) obj;
		if (diaThue == null) {
			if (other.diaThue != null)
				return false;
		} else if (!diaThue.equals(other.diaThue))
			return false;
		if (phieuThue == null) {
			if (other.phieuThue != null)
				return false;
		} else if (!phieuThue.equals(other.phieuThue))
			return false;
		return true;
	}
}
