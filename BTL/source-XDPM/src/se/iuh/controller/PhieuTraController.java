package se.iuh.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.database.HibernateUtil;
import se.iuh.model.Dia;
import se.iuh.model.PhieuTra;


public class PhieuTraController extends GeneralCRUD<PhieuTra> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();
	
	public PhieuTra getPhieuTraTheoMa(String maPhieuTra, Session session) {
		return session.get(PhieuTra.class, maPhieuTra);
	}
	public void updateSauTraDia(Dia diaThue) {
		session = sessionFactory.openSession();
		diaThue.setTrangThai("có sẵn");
	    new DiaController().update(diaThue, session);
	}
	public void updateOnHold(Dia diaThue) {
		session = sessionFactory.openSession();
		diaThue.setTrangThai("đang tạm giữ");
	    new DiaController().update(diaThue, session);
	}
}
