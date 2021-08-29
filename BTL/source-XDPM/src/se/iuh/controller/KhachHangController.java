package se.iuh.controller;

import org.hibernate.Session;

import se.iuh.model.KhachHang;

public class KhachHangController extends GeneralCRUD<KhachHang> {
	public KhachHang getKhachHang(String maKhachHang, Session session) {
		return session.get(KhachHang.class, maKhachHang);
	}
	// Them Tua Dia
	public void themKH(KhachHang KH, Session session) {
		insert(KH, session);
	}
	public void suaKH(KhachHang KH, Session session) {
		update(KH, session);
	}
	public KhachHang getKH(String makh, Session session) {
		return session.get(KhachHang.class, makh);
	}
}