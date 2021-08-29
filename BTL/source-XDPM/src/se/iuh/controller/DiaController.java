package se.iuh.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import se.iuh.model.Dia;

public class DiaController extends GeneralCRUD<Dia> {
	public Dia getDia(String maDia, Session session) {
		return session.get(Dia.class, maDia);
	}
	
	public List<Dia> timKiemTheoTuKhoa(String keyword, Session session) {
		String hql = "From Dia d where d.maDia LIKE :keyword OR d.tuaDia.maTua LIKE :keyword OR d.tuaDia.tenTua LIKE :keyword OR d.tuaDia.moTa LIKE :keyword OR d.tuaDia.loaiTua LIKE :keyword";
		Query<Dia> query = session.createQuery(hql);
		query.setParameter("keyword", "%"+keyword+"%");
		List<Dia> list = query.list();
		return list;
	}
	
	public List<Dia> getAllDiaTheoMaTua(String maTua, Session session) {
		String hql = "From Dia d where d.tuaDia.maTua = :maTua";
		Query<Dia> query = session.createQuery(hql);
		query.setParameter("maTua", maTua);
		List<Dia> list = query.list();
		return list;
	}
}
