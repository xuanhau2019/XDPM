package se.iuh.controller;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import se.iuh.model.PhiTreHan;

public class PhiTreHanController extends GeneralCRUD<PhiTreHan> {
	
	public PhiTreHan getPhiTreHan(String maPhiTreHan, Session session) {
		return session.get(PhiTreHan.class, maPhiTreHan);
	}
	
	public List<PhiTreHan> getListPhiTreHanChuaTraTheoMaKH(String maKH, Session session) {
		String hql = "From PhiTreHan pt where pt.phieuTra.phieuThue.khachHang.maKH = :maKH AND "
				+ "pt.maPhiTreHan NOT IN (select tt.ttPhi.maPhiTreHan from ThanhToanTreHan tt where tt.ttPhi.phieuTra = pt.phieuTra)";
		Query<PhiTreHan> query = session.createQuery(hql);
		query.setParameter("maKH", maKH);
		List<PhiTreHan> list = query.list();
		return list;
	}
	
	public List<PhiTreHan> getListPhiTreHanChuaTraTheoNhieuDieuKien(String maKH, String maDia, Date ngayTra, Session session) {
		String hql = "From PhiTreHan pt where ";
		int tokenCount = 0;
		if(maKH != null) {
			hql += "pt.phieuTra.phieuThue.khachHang.maKH = :maKH ";
			tokenCount++;
		}
		if(maDia != null) {
			if(tokenCount > 0)
				hql += "AND ";
			hql += "pt.phieuTra.dia.maDia = :maDia ";
			tokenCount++;
		}
		if(ngayTra != null) {
			if(tokenCount > 0)
				hql += "AND ";
			hql += "pt.phieuTra.ngayTra = :ngayTra ";
			tokenCount++;
		}
		if(maKH == null && maDia == null && ngayTra == null)
			return null;
		hql	+= "AND pt.maPhiTreHan NOT IN (select tt.ttPhi.maPhiTreHan from ThanhToanTreHan tt where tt.ttPhi.phieuTra = pt.phieuTra)";
		Query<PhiTreHan> query = session.createQuery(hql);
		if(maKH != null)
			query.setParameter("maKH", maKH);
		if(maDia != null)
			query.setParameter("maDia", maDia);
		if(ngayTra != null)
			query.setParameter("ngayTra", ngayTra);
		List<PhiTreHan> list = query.list();
		return list;
	}
}
