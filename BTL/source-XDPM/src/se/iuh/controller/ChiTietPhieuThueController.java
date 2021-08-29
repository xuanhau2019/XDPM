package se.iuh.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import se.iuh.model.ChiTietPhieuThue;

public class ChiTietPhieuThueController extends GeneralCRUD<ChiTietPhieuThue> {
	
	public List<ChiTietPhieuThue> getCTPTTheoMaKH(String maKH, Session session) {
		String hql = "From ChiTietPhieuThue ct where ct.phieuThue.khachHang.maKH = :maKH";
		Query<ChiTietPhieuThue> query = session.createQuery(hql);
		query.setParameter("maKH", maKH);
		List<ChiTietPhieuThue> list = query.list();
		return list;
	}
	
	public List<ChiTietPhieuThue> getCTPTTChuaTraTheoMaKH(String maKH, Session session) {
		String hql = "From ChiTietPhieuThue ct where ct.phieuThue.khachHang.maKH = :maKH AND ct.diaThue.maDia NOT IN (select pt.dia.maDia from PhieuTra pt where pt.phieuThue = ct.phieuThue)";
		Query<ChiTietPhieuThue> query = session.createQuery(hql);
		query.setParameter("maKH", maKH);
		List<ChiTietPhieuThue> list = query.list();
		return list;
	}
	
	public List<ChiTietPhieuThue> timCTPTTheoDiaThuePhieuThue(String maDia, String maPhieuThue, Session session) {
		String hql = "From ChiTietPhieuThue ct where ct.diaThue.maDia = :maDia AND ct.phieuThue.maPhieuThue = :maPhieuThue";
		Query<ChiTietPhieuThue> query = session.createQuery(hql);
		query.setParameter("maDia", maDia);
		query.setParameter("maPhieuThue", maPhieuThue);
		List<ChiTietPhieuThue> list = query.list();
		return list;
	}
	
	public List<ChiTietPhieuThue> getCTPTTChuaTraTheoTuaDia(String maTua, Session session) {
		String hql = "From ChiTietPhieuThue ct where ct.diaThue.tuaDia.maTua = :maTua AND ct.diaThue.maDia NOT IN (select pt.dia.maDia from PhieuTra pt where pt.phieuThue = ct.phieuThue)";
		Query<ChiTietPhieuThue> query = session.createQuery(hql);
		query.setParameter("maTua", maTua);
		List<ChiTietPhieuThue> list = query.list();
		return list;
	}
	
	public List<ChiTietPhieuThue> getCTPTTChuaTraTheoMaDia(String maDia, Session session) {
		String hql = "From ChiTietPhieuThue ct where ct.diaThue.maDia = :maDia AND ct.diaThue.maDia"
				+ " NOT IN (select p.dia.maDia from PhieuTra p where p.phieuThue = ct.phieuThue)";
		Query<ChiTietPhieuThue> query = session.createQuery(hql);
		query.setParameter("maDia", maDia);
		List<ChiTietPhieuThue> list = query.list();
		return list;
	}
}
