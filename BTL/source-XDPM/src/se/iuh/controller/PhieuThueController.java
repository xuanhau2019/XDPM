package se.iuh.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.database.HibernateUtil;
import se.iuh.model.ChiTietPhieuThue;
import se.iuh.model.Dia;
import se.iuh.model.KhachHang;
import se.iuh.model.NhanVien;
import se.iuh.model.PhieuThue;

public class PhieuThueController extends GeneralCRUD<PhieuThue> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();
	private List<ChiTietPhieuThue> listCTPT = new ArrayList<ChiTietPhieuThue>();
	private PhieuThue phieuThue;
	
	public PhieuThue getPhThue(String maPhieuThue, Session session) {
		return session.get(PhieuThue.class, maPhieuThue);
	}
	
	public boolean KiemTraMaKhachHang(String maKhachHang)
	{
		
		session = sessionFactory.openSession();
		// get danh sách
		List<KhachHang> listKhachHang = new KhachHangController().getAll(KhachHang.class, session);
		for (KhachHang khachHang : listKhachHang) {
			if(maKhachHang.equalsIgnoreCase(khachHang.getMaKH())) {
				session.close();
				return true;
			}
		}
		session.close();
		return false;
	}
	
	public KhachHang getKhachHangTheoMa(String maKhachHang)
	{
		session = sessionFactory.openSession();
		List<KhachHang> listKhachHang = new KhachHangController().getAll(KhachHang.class, session);
		for (KhachHang khachHang : listKhachHang) {
			if(maKhachHang.equalsIgnoreCase(khachHang.getMaKH())) {
				session.close();
				return khachHang;
			}
		}
		session.close();
		return null;
	}
	
	public boolean KiemTraMaDia(String maDia)
	{
		
		session = sessionFactory.openSession();
		// get danh sách
		List<Dia> listDia = new DiaController().getAll(Dia.class, session);
		for (Dia dia : listDia) {
			if(maDia.equalsIgnoreCase(dia.getMaDia())) {
				session.close();
				return true;
			}
		}
		session.close();
		return false;
	}
	
	public Dia getDiaTheoMa(String maDia)
	{
		session = sessionFactory.openSession();
		
		Dia diaCurr = (Dia) session.get(Dia.class, maDia);
		session.close();
		return diaCurr;
	}
	public void insertThongTinThueDia(Dia diaThue, Date ngayThue, Date hanTraDia, KhachHang khachHang, NhanVien nhanVien)
	{
		// new chi tiet phieu thue
		// kiem tra phieu thue
		//	neu phieu thue chua co
		//  	new phieu thue va add chi tiet phieu thue da tao vao
		//	chi can add chi tiet phieu thue vao thoi
		
		session = sessionFactory.openSession();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		 
	    int month = c.get(Calendar.MONTH)+1;
	    int day = c.get(Calendar.DAY_OF_MONTH);
	    int hour = c.get(Calendar.HOUR_OF_DAY);
	    int minute = c.get(Calendar.MINUTE);
	    int second = c.get(Calendar.SECOND);
	    int mils = c.get(Calendar.MILLISECOND);
	    String maCTPT = "CT"+year+month+day+hour+minute+mils;
	    String maPhieuThue = "PT"+year+month+day+hour+minute+mils;
	    phieuThue = new PhieuThue(maPhieuThue, khachHang, nhanVien, ngayThue, listCTPT);
	    diaThue.setTrangThai("đang được thuê");
	    new DiaController().update(diaThue, session);
		ChiTietPhieuThue chiTietPhieuThue = new ChiTietPhieuThue(maCTPT, diaThue, phieuThue, diaThue.getTuaDia().getGiaThue()
				, diaThue.getTuaDia().getPhiTreHan(), hanTraDia);
		listCTPT.add(chiTietPhieuThue);
			
		PhieuThueController phieuThueController = new PhieuThueController();
		phieuThueController.insert(phieuThue, session);
			
		session.close();
	}
	
	
}
