package se.iuh.controller;

import org.hibernate.Session;

import se.iuh.model.NhanVien;

public class NhanVienController extends GeneralCRUD<NhanVien> {
	public NhanVien getNhanVien(String maNV, Session session) {
		return session.get(NhanVien.class, maNV);
	}
}
